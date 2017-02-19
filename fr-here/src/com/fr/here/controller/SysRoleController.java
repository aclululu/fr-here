package com.fr.here.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fr.here.dao.SysRole_PrivilegeMapper;
import com.fr.here.model.SysRole;
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
public class SysRoleController {
	private Logger log = Logger.getLogger(SysRoleController.class);
	@Resource
	private SysRoleService roleService;
	@Resource
	private SysPrivilegeService privilegeService;
	//@Resource
	//private SysRole_PrivilegeMapper rpService;
	@Resource
	private SysUserService userService;
	
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/role/add")
	public ModelAndView addRole(SysRole model) {
		 Map<String,Object> data;
		if(roleService.isExist(model.getName())){
			data = AjaxResult.getMessageMap(C.MESSAGE_ROLE_EXIST, 300);
		}else{
			if(roleService.addSysRole(model)>0){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	/** 跳转到添加 **/
	@RequestMapping(value = "/sys/role/toadd")
	public String toAddRole(HttpServletRequest request) {
		return "admin/permission/addRole";
	}
	
	
	/**
	 *更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/role/update")
	public ModelAndView updateRole(SysRole model) {
		 Map<String,Object> data;
		 SysRole role = roleService.findSysRole(model.getId());
		 if(role.getName().equals(model.getName())){
			 if(roleService.updateSysRole(model)){
					data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
				}else{
					data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
				}
		 }else{
			 if(roleService.isExist(model.getName())){
				 data = AjaxResult.getMessageMap(C.MESSAGE_ROLE_EXIST, 300);
				}else{
					if(roleService.updateSysRole(model)){
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
	@RequestMapping(value = "/sys/role/toupdate")
	public String toUpdateRole(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", roleService.findSysRole(id));
		model.addAttribute("model",roleService.findSysRole(id));
		return "admin/permission/updateRole";
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sys/role/delete")
	public  ModelAndView deleteRole(@RequestParam("id")int id) {
		 Map<String,Object> data;
		 int[] ids = new int[]{id};
		return deleteRoles(ids);   
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/sys/role/deletes")
	public  ModelAndView deleteRoles(@RequestParam("ids") int[] ids) {
		 Map<String,Object> data;
		 int flag = userService.findCountByRoleIDs(ids);
		 if(flag>0){
			 data = AjaxResult.getMessageMap(String.format(C.MESSAGE_ROLE_HAS_USE, flag), 300);
		 }else{
			 if(roleService.deletePrivilegesByRoleIDS(ids)){
				 if(roleService.deleteSysRoles(ids)){
					 data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
				 }else{
					 data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
				 }
			 }else{
				 data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			 }
		 }
		 return new ModelAndView("admin/dwz/ajaxDone",data); 
	}
	
	@RequestMapping(value = "/sys/role/list")
	//@RequestParam("pageNum")int pageNum,@RequestParam("likename")String likename
	public ModelAndView listRole(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		//int pageNum = (Integer) request.getAttribute("pageNum");
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		 String likename = (String) request.getParameter("likename");
		 Map<String,Object> data = new HashMap<String, Object>();
		 pageBean.setPageSize(numPerPage);
		 pageBean = roleService.findSysRoles(likename, pageNum, null, null, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("numPerPage", numPerPage);
		return new ModelAndView("admin/permission/listRole",data);
	}
	

	/**
	 * 跳转至分配权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/role/toassignprivilege")
	public String toAssignPrivilege(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("model",roleService.findSysRole(id));
		List tree = privilegeService.makePrivilegeTree(null,roleService.findPrivilegesByRoleID(id));
		model.addAttribute("tree", tree);
		return "admin/permission/assignPrivilege";
	}
	
	

	@RequestMapping(value = "/sys/role/assignprivilege")
	public  ModelAndView assignPrivilege(@RequestParam("roleid")int roleid,@RequestParam(value = "name",required=false ) int[] names ) {
		Map<String,Object> data;
		if(names==null){
			names = new int[]{};
		}
			if(roleService.deletePrivilegesByRoleIDS(new int[]{roleid})&&roleService.addSysRole_Privileges(roleid,names)){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	

}

