package com.fr.here.controller;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fr.here.model.SupplyColumn;
import com.fr.here.service.SupplyColumnService;
import com.fr.here.service.SupplyService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;

/**
 * 新闻类别
 * @author shli
 */
@Controller
public class SupplyColumnController {
	private Logger log = Logger.getLogger(SupplyColumnController.class);
	@Resource
	private SupplyColumnService columnService;
	@Resource
	private SupplyService supplyService;
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/supplycolumn/add")
	public ModelAndView addSupplyColumn(SupplyColumn model) {
		Map<String,Object> data;
		if(columnService.addSupplyColumn(model)>0){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}

	/** 跳转到添加 **/
	@RequestMapping(value = "/sys/supplycolumn/toadd")
	public String toAddUser(HttpServletRequest request) {
		return "admin/supply/addSupplyColumn";
	}
	
	/**
	 *更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/supplycolumn/update")
	public ModelAndView updateUser(SupplyColumn model) {
		Map<String,Object> data;
		if(columnService.updateSupplyColumn(model)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}

	/**
	 * 跳转至更新
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/supplycolumn/toupdate")
	public String toUpdateUser(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("model",columnService.findSupplyColumn(id));
		return "admin/supply/updateSupplyColumn";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sys/supplycolumn/delete")
	public  ModelAndView deleteUser(@RequestParam("id")int id) {
		 Map<String,Object> data;
		 //检测是否有新闻关联此新闻类别
		 int flag = supplyService.columnHasSupply(id);
		 if(flag>0){
			 data = AjaxResult.getMessageMap(String.format(C.MESSAGE_NEWSCOLUMN_HAS_USE, flag), 300);
		 }else{
			if(columnService.deleteSupplyColumn(id)){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		 }
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	
	/**
	 * 分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/supplycolumn/list")
	public ModelAndView listUser(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		//int pageNum = (Integer) request.getAttribute("pageNum");
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		 String likename = (String) request.getParameter("likename");
		 Map<String,Object> data = new HashMap<String, Object>();
		 pageBean.setPageSize(numPerPage);
		 
		 //将参数传回去
		 pageBean = columnService.findSupplyColumns(likename, pageNum, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("numPerPage", numPerPage);
		return new ModelAndView("admin/supply/listSupplyColumn",data);
	}
	
}

