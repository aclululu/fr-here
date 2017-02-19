package com.fr.here.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.fr.here.model.SysPrivilege;
import com.fr.here.model.SysUser;
import com.fr.here.service.SysPrivilegeService;
import com.fr.here.service.SysRoleService;
import com.fr.here.service.SysUserService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;

/**
 * @author shli
 * 2015-7-1
 */
@Controller
public class SysUserController {
	private Logger log = Logger.getLogger(SysUserController.class);
	@Resource
	private SysUserService userService;
	@Resource
	private SysRoleService roleService;
	@Resource
	private SysPrivilegeService privilegeService;
	
	/**
	 * 登录
	 * @param request
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/sys/login")
	public ModelAndView login(HttpServletRequest request){
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Map<String,Object> data = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(30*60);
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
			String user = (String) session.getAttribute("user");
			Map<String,Object> userdata = (Map<String, Object>) session.getAttribute("data");
			if(user!=null&&userdata!=null){
				return new ModelAndView("admin/index",userdata);
			}else{
				request.setAttribute("tipMessage", "用户名和密码不能为空");
				return new ModelAndView("admin/login/login");	
			}
		}
		SysUser user = userService.findSysUser(name);
		if(user!=null){
			if(user.getStatus()<0){
				request.setAttribute("tipMessage", "账号已被锁定，请联系管理员解锁");
				return new ModelAndView("admin/login/login");
			}
			if(user.getPassword().equals(password)){
				session.setAttribute("user", user.getAccount());
				List<SysPrivilege> privileges =  roleService.findPrivilegesByRoleID(user.getRoleid());
				//构建权限树
				data.put("tree",privilegeService.makePrivilegeTree(privileges, null));
				session.setAttribute("data", data);
			}else{
				request.setAttribute("tipMessage", "用户名或者密码错误");
				return new ModelAndView("admin/login/login");
			}
		}else{
			request.setAttribute("tipMessage", "用户名或者密码错误");
			return new ModelAndView("admin/login/login");
		}
		return new ModelAndView("admin/index",data);
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("data");
		return "admin/login/login";
	}
	
	
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/user/add")
	public ModelAndView addUser(SysUser model) {
		 Map<String,Object> data;
		if(userService.isExist(model.getAccount())){
			data = AjaxResult.getMessageMap(C.MESSAGE_SYSUSER_EXIST, 300);
		}else{
			if(userService.addSysUser(model)>0){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	

	/** 跳转到添加 **/
	@RequestMapping(value = "/sys/user/toadd")
	public String toAddUser(HttpServletRequest request) {
		return "admin/permission/addUser";
	}
	
	/**
	 *更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/user/update")
	public ModelAndView updateUser(SysUser model) {
		 Map<String,Object> data;
		 SysUser user = userService.findSysUser(model.getId());
		 if(user.getAccount().equals(model.getAccount())){
			 if(userService.updateSysUser(model)){
					data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
				}else{
					data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
				}
		 }else{
			 if(userService.isExist(model.getAccount())){
				 data = AjaxResult.getMessageMap(C.MESSAGE_SYSUSER_EXIST, 300);
				}else{
					if(userService.updateSysUser(model)){
						data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
					}else{
						data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
					}
				} 
		 }
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	

	/**
	 * 跳转至更新
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/user/toupdate")
	public String toUpdateUser(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("model",userService.findSysUser(id));
		return "admin/permission/updateUser";
	}
	
	/**
	 * 跳转至分配角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/user/toassignrole")
	public String toAssignRole(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("model",userService.findSysUser(id));
		List roles = roleService.findSysRolesByCond(new HashMap());
		model.addAttribute("roles", roles);
		return "admin/permission/assignRole";
	}
	
	
	
	
	
	@RequestMapping(value = "/sys/user/delete")
	public  ModelAndView deleteUser(@RequestParam("id")int id) {
		 Map<String,Object> data;
		if(userService.deleteSysUser(id)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	@RequestMapping(value = "/sys/user/deletes")
	//@RequestParam("ids")int[] ids
	public  ModelAndView deleteUsers(@RequestParam("ids") int[] ids) {
		 Map<String,Object> data;
		 //int[] ids = request.getParameter("ids");
		if(userService.deleteSysUsers(ids)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	@RequestMapping(value = "/sys/user/list")
	//@RequestParam("pageNum")int pageNum,@RequestParam("likename")String likename
	public ModelAndView listUser(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		//int pageNum = (Integer) request.getAttribute("pageNum");
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		 String status = request.getParameter("condstatus");
		 
		 int condstatus = status!=null&&!status.equals("") ? Integer.valueOf(status) :0;
		 String likename = (String) request.getParameter("likename");
		 Map<String,Object> data = new HashMap<String, Object>();
		 pageBean.setPageSize(numPerPage);
		 
		 /**
		  * 将参数传回去
		  */
		 pageBean = userService.findSysUsers(condstatus,likename, pageNum, null, null, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("condstatus", condstatus);
		 data.put("numPerPage", numPerPage);
		return new ModelAndView("admin/permission/listUser",data);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

