package com.fr.here.controller;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fr.here.model.News;
import com.fr.here.model.Supply;
import com.fr.here.service.SupplyColumnService;
import com.fr.here.service.SupplyService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;
import com.fr.weixin.pojo.SNSUserInfo;
/**
 * @author shli
 * 2015-7-1
 */
@Controller
public class SupplyController {
	private Logger log = Logger.getLogger(SupplyController.class);
	@Resource
	private SupplyService supplyService;
	@Resource
	private SupplyColumnService columnService;
	  /**
	   * 添加
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/sys/supply/addorupdate")
	  public ModelAndView addOrUpdateSupply(HttpServletRequest request,Supply model) {
		//对一些属性进行初始化操作
	    Map<String,Object> data;
	    model.setCdate(new Date());
	    model.setStatus(-1);
	    model.setSuname((String)request.getSession().getAttribute("user"));
	    boolean flag = false;
	    if(model.getId()!=null&&model.getId()>0){
	    	flag = supplyService.updateSupply(model);
	    }else{
	    	flag = supplyService.addSupply(model)>0;
	    }
	    if(flag){
	      data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
	    }else{
	      data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
	    }
	    return new ModelAndView("admin/dwz/ajaxDone",data);   
	  }

	
	/**
	 * 跳转至更新  添加
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sys/supply/toaddorupdate")
	public String toUpdateSupply(@RequestParam(value ="id",required=false)int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("supplycolumns", columnService.findSupplyColumns(new HashMap()));
		Supply supply;
		if(id>0){
			supply = supplyService.findSupply(id);
		}else{
			supply  =  new Supply();
		}
		model.addAttribute("model",supply);
		return "admin/supply/addSupply";
	}
	
	/**
	 * 微信公众号  添加供需
	 */
	@RequestMapping(value = "/app/supply/toadd")
	public String toAddSupply(HttpServletRequest request,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		SNSUserInfo snsUserInfo = (SNSUserInfo) request.getAttribute("snsUserInfo");
		//System.out.println(snsUserInfo.toString());
		request.getSession().setAttribute("snsUserInfo", snsUserInfo);
		model.addAttribute("supplycolumns", columnService.findSupplyColumns(new HashMap()));
		return "app/addSupply";
	}
	
	  /**
	   * 微信公众号  添加供需
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/app/supply/add")
	  public ModelAndView addSupply(HttpServletRequest request,Supply model) {
		//对一些属性进行初始化操作
	    Map<String,Object> data = new HashMap<String, Object>();
	    model.setCdate(new Date());
	    model.setStatus(-1);
	    //snsUserInfo
	    SNSUserInfo snsUserInfo = (SNSUserInfo) request.getSession().getAttribute("snsUserInfo");
	    model.setSuname(snsUserInfo.getNickname()+"_wx");
	    model.setOpenid(snsUserInfo.getOpenId());
	    boolean flag = false;
	    int id = supplyService.addSupply(model);
	    flag = id >0;
	    if(flag){
	    	data.put("id", id);
	    	data.put("message", "添加成功，等到管理员审核成功之后就可以看到");
	       return new ModelAndView("app/addSuccess",data);   
	    }else{
	    	data.put("message", "对不起，添加失败你可以重新添加或者在公众号中给我们留言反馈");
	       return new ModelAndView("app/readSupply",data);   
	    }
	  }
	
	
	
	
	/**
	 * 根据id获取一条供需
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/app/supply/view")
	public String readNews(@RequestParam("id")int id,ModelMap model){
		Supply supply = supplyService.findSupply(id);
		model.addAttribute("model",supply);
		return "app/readSupply";
	}
	
	@RequestMapping(value = "/sys/supply/delete")
	public  ModelAndView deleteSupply(@RequestParam("id")int id) {
		 Map<String,Object> data;
		if(supplyService.deleteSupply(id)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	@RequestMapping(value = "/sys/supply/deletes")
	public  ModelAndView deleteSupplys(@RequestParam("ids") int[] ids) {
		 Map<String,Object> data;
		if(supplyService.deleteSupply(ids)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	/** 
	 * 发布或者撤销发布一条记录  和原来相反
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sys/supply/publish")
	public  ModelAndView publishSupply(@RequestParam("id")int id,@RequestParam("status")int status) {
		 Map<String,Object> data;
		 int flag = status > 0 ? -1:1;
		if(supplyService.updateSupplyStatus(id,flag)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	/**
	 * 发布或者撤销发布多条记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sys/supply/publishs")
	public  ModelAndView publishSupplys(@RequestParam("ids") int[] ids,@RequestParam("status")int status) {
		 Map<String,Object> data;
		if(supplyService.updateSupplyStatus(ids,status)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	

	@RequestMapping(value = "/sys/supply/list")
	public ModelAndView listUser(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		
		 String columnid = request.getParameter("columnid");
		 String startdate = request.getParameter("startdate");
		 String enddate = request.getParameter("enddate");
		 String condsio = request.getParameter("condsio");
		 String orderField = request.getParameter("orderField");
		 String orderDirection = request.getParameter("orderDirection");
		 String condstatus = request.getParameter("condstatus");
		 String likename =  request.getParameter("likename");
		 
		 orderField = orderField == null||orderField.equals("") ?"id":orderField;
		 orderDirection = orderDirection == null||orderDirection.equals("") ?  "desc":orderDirection;
		 HashMap<String,Object> cond = new HashMap<String, Object>();
		 cond.put("likename", likename);
		 cond.put("cid", columnid);
		 cond.put("startDate", startdate);
		 cond.put("endDate", enddate);
		 cond.put("sio", condsio);
		 cond.put("sort", orderField);
		 cond.put("order", orderDirection);
		 cond.put("status", condstatus);
		 pageBean.setPageSize(numPerPage);
		 /**
		  * 将参数传回去
		  */
		 pageBean = supplyService.findSupply(cond,pageNum, pageBean);
		 Map<String,Object> data = new HashMap<String, Object>();
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("columnid", columnid);
		 data.put("condsio", condsio);
		 data.put("startdate", startdate);
		 data.put("enddate", enddate);
		 data.put("condstatus", condstatus);
		 data.put("numPerPage", numPerPage);
		 data.put("orderField", orderField);
		 data.put("orderDirection", orderDirection);
		 data.put("supplycolumns", columnService.findSupplyColumns(new HashMap()));
		return new ModelAndView("admin/supply/listSupply",data);
	}
}

