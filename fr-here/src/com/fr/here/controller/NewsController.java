package com.fr.here.controller;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fr.here.model.AppWelcome;
import com.fr.here.model.News;
import com.fr.here.model.base.ResponseJson;
import com.fr.here.service.NewsColumnService;
import com.fr.here.service.NewsService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;
/**
 * @author shli
 * 2015-7-1
 */
@Controller
public class NewsController {
	private Logger log = Logger.getLogger(NewsController.class);
	@Resource
	private NewsService newsService;
	@Resource
	private NewsColumnService columnService;
	  /**
	   * 添加
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/sys/news/addorupdate")
	  public ModelAndView addOrUpdateNews(HttpServletRequest request,News model,@RequestParam(value ="file",required=false)MultipartFile file) {
		//对一些属性进行初始化操作
	    Map<String,Object> data;
	    model.setCdate(new Date());
	    model.setStatus(-1);
	    model.setEditor((String)request.getSession().getAttribute("user"));
	    if(file!=null&&file.getSize()>0){
	    	try {
	    		model.setPicnews(1);
				model.setPicture(uploadPictures(request, file));
			} catch (Exception e) {
				model.setPicnews(-1);
				e.printStackTrace();
			}
	    }else{
	    	model.setPicture(null);
	    	model.setPicnews(-1);
	    }
	    boolean flag = false;
	    if(model.getId()!=null&&model.getId()>0){
	    	flag = newsService.updateNews(model);
	    }else{
	    	flag = newsService.addNews(model)>0;
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
	@RequestMapping(value = "/sys/news/toaddorupdate")
	public String toUpdateNews(@RequestParam(value ="id",required=false)int id,ModelMap model) {
		//request.setAttribute("model", userService.findSysUser(id));
		model.addAttribute("newscolumns", columnService.findNewsColumns(new HashMap()));
		News news;
		if(id>0){
			news = newsService.findNews(id);
		}else{
			news  =  new News();
			news.setPriority(0);
			news.setClicks(0);
		}
		model.addAttribute("model",news);
		return "admin/news/addNews";
	}
	
	/**
	 * 根据id获取一条新闻
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/app/news/view")
	public String readNews(@RequestParam("id")int id,ModelMap model){
		News news = newsService.findNews(id);
		model.addAttribute("model",news);
		return "app/readNews";
	}
	
	
	@RequestMapping(value = "/sys/news/delete")
	public  ModelAndView deleteNews(@RequestParam("id")int id) {
		 Map<String,Object> data;
		if(newsService.deleteNews(id)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	@RequestMapping(value = "/sys/news/deletes")
	public  ModelAndView deleteNewss(@RequestParam("ids") int[] ids) {
		 Map<String,Object> data;
		if(newsService.deleteNews(ids)){
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
	@RequestMapping(value = "/sys/news/publish")
	public  ModelAndView publishNews(@RequestParam("id")int id,@RequestParam("status")int status) {
		 Map<String,Object> data;
		 int flag = status > 0 ? -1:1;
		if(newsService.updateNewsStatus(id,flag)){
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
	@RequestMapping(value = "/sys/news/publishs")
	public  ModelAndView publishNewss(@RequestParam("ids") int[] ids,@RequestParam("status")int status) {
		 Map<String,Object> data;
		if(newsService.updateNewsStatus(ids,status)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	

	@RequestMapping(value = "/sys/news/list")
	public ModelAndView listUser(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		
		 String columnid = request.getParameter("columnid");
		 String startdate = request.getParameter("startdate");
		 String enddate = request.getParameter("enddate");
		 String likeeditor = request.getParameter("likeeditor");
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
		 cond.put("likeeditor", likeeditor);
		 cond.put("sort", orderField);
		 cond.put("order", orderDirection);
		 cond.put("status", condstatus);
		 pageBean.setPageSize(numPerPage);
		 /**
		  * 将参数传回去
		  */
		 pageBean = newsService.findNews(cond,pageNum, pageBean);
		 Map<String,Object> data = new HashMap<String, Object>();
		 data.put("pageBean", pageBean);
		 data.put("likename", likename);
		 data.put("columnid", columnid);
		 data.put("likeeditor", likeeditor);
		 data.put("startdate", startdate);
		 data.put("enddate", enddate);
		 data.put("condstatus", condstatus);
		 data.put("numPerPage", numPerPage);
		 data.put("orderField", orderField);
		 data.put("orderDirection", orderDirection);
		 data.put("newscolumns", columnService.findNewsColumns(new HashMap()));
		return new ModelAndView("admin/news/listNews",data);
	}
	
	/**
	 * 图片上传
	 * @param request
	 * @param updatefileName   源文件名
	 * @param upload     文件
	 * @return   上传后的路径
	 * @throws Exception
	 */
	public String uploadPictures(HttpServletRequest request,MultipartFile file) throws Exception {
		
		String fileName=file.getOriginalFilename();
		String uploadContentType =file.getContentType();
		String expandedName ="";
		if (uploadContentType.equals("image/pjpeg")  
                || uploadContentType.equals("image/jpeg")) {  
            // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
            expandedName = ".jpg";  
        } else if (uploadContentType.equals("image/png")  
                || uploadContentType.equals("image/x-png")) {  
            // IE6上传的png图片的headimageContentType是"image/x-png"  
            expandedName = ".png";  
        } else if (uploadContentType.equals("image/gif")) {  
            expandedName = ".gif";  
        } else if (uploadContentType.equals("image/bmp")) {  
            expandedName = ".bmp";  
        } else {  
        	throw new RuntimeException("文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）");
        }  
		if (file.getSize()> 1024 * 1024 * 5) { 
			throw new RuntimeException("文件大小不得大于5M");
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = "news_"+df.format(new Date())+expandedName;
	    String uploadPath = request.getSession().getServletContext().getRealPath("/news_image")+File.separatorChar;   //设置保存目录  
	    File file1 =new File(uploadPath);    
        //如果文件夹不存在则创建    
        if  (!file1.exists()  && !file1 .isDirectory())      
        {       
            file1.mkdir();    
        }   
		
		file.transferTo(new File(uploadPath +fileName)); 
        return "/fr-here/news_image/"+fileName;  
	}
	
	
	
	/**
	 * app获取新闻列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/app/news/list")
	public ResponseJson<News> dataNewsList(@RequestParam("picnews")int picnews,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
		ResponseJson<News> rj = new ResponseJson<News>();
		List<News>  newss = newsService.findNews(pageNum, pageSize, picnews);
		for(News news : newss){
			news.setContent(null);
		}
		rj.setList(newss);
		return rj;
	}
	
	/**
	 * app获取banner新闻
	 */
	@ResponseBody
	@RequestMapping(value="/app/news/banner")
	public ResponseJson<News> dataNewsBanner(){
		ResponseJson<News> rj = new ResponseJson<News>();
		List<News>  newss = newsService.getBannerNews();
		for(News news : newss){
			news.setContent(null);
		}
		rj.setList(newss);
		return rj;
	}
	
}

