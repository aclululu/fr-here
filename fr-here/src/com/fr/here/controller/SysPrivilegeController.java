package com.fr.here.controller;
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
import com.fr.here.model.SysPrivilege;
import com.fr.here.service.SysPrivilegeService;
import com.fr.here.service.SysRoleService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;

/**
 * @author shli
 * 2015-7-1
 */
@Controller
public class SysPrivilegeController {
	private Logger log = Logger.getLogger(SysPrivilegeController.class);
	@Resource
	private SysPrivilegeService privilegeService;
	@Resource
	private SysRoleService roleService;
	
	@RequestMapping({"/sys","/","/sys/"})
	public String go(){
		return "admin/login/login";
	}
	
	/**
	 * 添加权限
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/privilege/add")
	public ModelAndView addPrivilege(SysPrivilege model) {
		 Map<String,Object> data;
		if(privilegeService.isExist(model.getName())){
			data = AjaxResult.getMessageMap(C.MESSAGE_PRIVILEGE_EXIST, 300);
		}else{
			if(privilegeService.addSysPrivilege(model)>0){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	/**
	 *更新权限
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/privilege/update")
	public ModelAndView updatePrivilege(SysPrivilege model) {
		 Map<String,Object> data;
		 SysPrivilege privilege = privilegeService.findSysPrivilege(model.getId());
		 if(privilege.getName().equals(model.getName())){
			 if(privilegeService.updateSysPrivilege(model)){
					data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
				}else{
					data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
				}
		 }else{
			 if(privilegeService.isExist(model.getName())){
				 data = AjaxResult.getMessageMap(C.MESSAGE_PRIVILEGE_EXIST, 300);
				}else{
					if(privilegeService.updateSysPrivilege(model)){
						data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
					}else{
						data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
					}
				} 
		 }
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	
	/** 跳转到添加权限 **/
	@RequestMapping(value = "/sys/privilege/toadd")
	public String toAddPrivilege(HttpServletRequest request) {
		List privileges = privilegeService.findBossSysPrivilege();
		request.setAttribute("privileges", privileges);
		return "admin/permission/addPrivilege";
	}
	/**
	 * 跳转至更新权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/privilege/toupdate")
	public String toUpdatePrivilege(HttpServletRequest request,@RequestParam("id")int id,ModelMap model) {
		List privileges = privilegeService.findBossSysPrivilege();
		request.setAttribute("privileges", privileges);
		//request.setAttribute("model", privilegeService.findSysPrivilege(id));
		model.addAttribute("model",privilegeService.findSysPrivilege(id));
		return "admin/permission/updatePrivilege";
	}
	
	
	@RequestMapping(value = "/sys/privilege/delete")
	public  ModelAndView deletePrivilege(@RequestParam("id")int id) {
		Map<String,Object> data;
		//判断需要删除的权限是否是顶级权限
		SysPrivilege privilege =  privilegeService.findSysPrivilege(id);
		List<SysPrivilege> privileges;
		int[] ids;
		if(privilege.getPid()==0){
			privileges = privilegeService.findChildSysPrivilege(id);
			ids = new int[privileges.size()+1];
			for (int i=0;i<privileges.size();i++) {
				ids[i] = privileges.get(i).getId();
			}
			ids[privileges.size()] = id;
		}else{
		    ids = new int[]{id};
		}
		
		if(roleService.deletePrivilegesByProvilgerIDs(ids)){
			if(privilegeService.deleteSysprivileges(ids)){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	@RequestMapping(value = "/sys/privilege/list")
	//@RequestParam("pageNum")int pageNum,@RequestParam("likename")String likename
	public ModelAndView listPrivilege(HttpServletRequest request){
		//int pageNum = (Integer) request.getAttribute("pageNum");
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 String likename = (String) request.getParameter("likename");
		 Map<String,Object> data = new HashMap<String, Object>();
		 PageBean pageBean = new PageBean();
		 pageBean = privilegeService.findSysPrivileges(likename, pageNum, null, null, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		return new ModelAndView("admin/permission/listPrivilege",data);
	}

}

