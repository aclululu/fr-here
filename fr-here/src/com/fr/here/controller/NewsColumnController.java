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
import com.fr.here.model.NewsColumn;
import com.fr.here.service.NewsColumnService;
import com.fr.here.service.NewsService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;

/**
 * 新闻类别
 * @author shli
 */
@Controller
public class NewsColumnController {
	private Logger log = Logger.getLogger(NewsColumnController.class);
	@Resource
	private NewsColumnService columnService;
	@Resource
	private NewsService newsService;
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/newscolumn/add")
	public ModelAndView addNewsColumn(NewsColumn model) {
		Map<String,Object> data;
		if(columnService.addNewsColumn(model)>0){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}

	/** 跳转到添加 **/
	@RequestMapping(value = "/sys/newscolumn/toadd")
	public String toAddUser(HttpServletRequest request) {
		return "admin/news/addNewsColumn";
	}
	
	/**
	 *更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/newscolumn/update")
	public ModelAndView updateUser(NewsColumn model) {
		Map<String,Object> data;
		if(columnService.updateNewsColumn(model)){
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
	@RequestMapping(value = "/sys/newscolumn/toupdate")
	public String toUpdateUser(@RequestParam("id")int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("model",columnService.findNewsColumn(id));
		return "admin/news/updateNewsColumn";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sys/newscolumn/delete")
	public  ModelAndView deleteUser(@RequestParam("id")int id) {
		 Map<String,Object> data;
		 //检测是否有新闻关联此新闻类别
		 int flag = newsService.columnHasNews(id);
		 if(flag>0){
			 data = AjaxResult.getMessageMap(String.format(C.MESSAGE_NEWSCOLUMN_HAS_USE, flag), 300);
		 }else{
			if(columnService.deleteNewsColumn(id)){
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
	@RequestMapping(value = "/sys/newscolumn/list")
	public ModelAndView listUser(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		//int pageNum = (Integer) request.getAttribute("pageNum");
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		 String likename = (String) request.getParameter("likename");
		 Map<String,Object> data = new HashMap<String, Object>();
		 pageBean.setPageSize(numPerPage);
		 
		 //将参数传回去
		 pageBean = columnService.findNewsColumns(likename, pageNum, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("numPerPage", numPerPage);
		return new ModelAndView("admin/news/listNewsColumn",data);
	}
	
}

