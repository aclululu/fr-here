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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fr.here.model.Movie;
import com.fr.here.model.base.ResponseJson;
import com.fr.here.service.MovieService;
import com.fr.here.util.AjaxResult;
import com.fr.here.util.C;
import com.fr.here.util.PageBean;

/**
 * @author shli
 * 2015-7-27
 */
@Controller
public class MovieController {
	@Resource
	private MovieService movieService;
	
	/**
	 * app分页获取
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/app/movie/datalist")
	public ResponseJson<Movie> dataMovieList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
		ResponseJson<Movie> rj = new ResponseJson<Movie>();
		List<Movie> movies = movieService.findMovie(pageNum, pageSize);
		rj.setList(movies);
		return rj;
	}
	
	/**
	 * 添加  更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/app/movie/addorupdate")
	public ModelAndView addOrUpdateMovie(HttpServletRequest request,Movie model,@RequestParam(value ="pic",required=false)MultipartFile pic,@RequestParam(value ="movie",required=false)MultipartFile movie) {
		 Map<String,Object> data;
		 if(pic!=null&&pic.getSize()>0){
		    	try {
					model.setPicurl(uploadPicture(request, pic));
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }else{
		    	model.setPicurl(null);
		    }
		 if(movie!=null&&movie.getSize()>0){
		    	try {
					model.setMovieurl(uploadMovie(request, movie));
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }else{
		    	model.setMovieurl(null);
		    }
		 
		 	model.setCdate(new Date());
		 	model.setCreator((String)request.getSession().getAttribute("user"));
		 
		 	boolean flag = false;
		    if(model.getId()!=null&&model.getId()>0){
		    	flag = movieService.updateMovie(model);
		    }else{
		    	flag = movieService.addMovie(model)>0;
		    }
		 
			if(flag){
				data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
			}else{
				data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
			}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	

	/** 跳转到添加或者添加 **/
	@RequestMapping(value = "/app/movie/toaddorupdate")
	public String toAddOrUpdateMovie(@RequestParam(value ="id",required=false)int id,ModelMap model) {
		Movie app;
		if(id>0){
			app = movieService.findMovie(id);
		}else{
			app = new Movie();
		}
		model.addAttribute("model",app);
		return "app/movie/addMovie";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/app/movie/delete")
	public  ModelAndView deleteMovie(@RequestParam("id")int id) {
		 Map<String,Object> data;
		if(movieService.deleteMovie(id)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/app/movie/deletes")
	public  ModelAndView deleteMovies(@RequestParam("ids") int[] ids) {
		 Map<String,Object> data;
		if(movieService.deleteMovies(ids)){
			data = AjaxResult.getMessageMap(C.MESSAGE_SUCCESS, 200);
		}else{
			data = AjaxResult.getMessageMap(C.MESSAGE_FAIL, 300);
		}
		return new ModelAndView("admin/dwz/ajaxDone",data);   
	}
	
	@RequestMapping(value = "/app/movie/list")
	//@RequestParam("pageNum")int pageNum,@RequestParam("likename")String likename
	public ModelAndView listMovie(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		 int pageNum = request.getParameter("pageNum")==null ? 1 :Integer.valueOf(request.getParameter("pageNum"));
		 int numPerPage = request.getParameter("numPerPage")==null? pageBean.getPageSize():Integer.valueOf(request.getParameter("numPerPage"));
		 Map<String,Object> data = new HashMap<String, Object>();
		 pageBean.setPageSize(numPerPage);
		 
		 /**
		  * 将参数传回去
		  */
		 pageBean = movieService.findMovies(pageNum, null, null, pageBean);
		 data.put("pageBean", pageBean);
		 data.put("numPerPage", numPerPage);
		return new ModelAndView("app/movie/listMovie",data);
	}
	
	
	/**
	 * 图片上传
	 * @param request
	 * @param updatefileName   源文件名
	 * @param upload     文件
	 * @return   上传后的路径
	 * @throws Exception
	 */
	public String uploadPicture(HttpServletRequest request,MultipartFile file) throws Exception {
		
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
        }else if (uploadContentType.equals("image/bmp")) {  
            expandedName = ".bmp";  
        } else {  
        	throw new RuntimeException("文件格式不正确（必须为.jpg/.bmp/.png文件）");
        }  
		if (file.getSize()> 1024 * 1024 * 5) { 
			throw new RuntimeException("文件大小不得大于5M");
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = "movie_"+df.format(new Date())+expandedName;
	    String uploadPath = request.getSession().getServletContext().getRealPath("/appmovie_image")+File.separatorChar;   //设置保存目录  
	    File file1 =new File(uploadPath);    
        //如果文件夹不存在则创建    
        if  (!file1.exists()  && !file1 .isDirectory())      
        {       
            file1.mkdir();    
        }   
		
		file.transferTo(new File(uploadPath +fileName)); 
        return "/fr-here/appmovie_image/"+fileName;  
	}
	
	
	/**
	 * 图片上传
	 * @param request
	 * @param updatefileName   源文件名
	 * @param upload     文件
	 * @return   上传后的路径
	 * @throws Exception
	 */
	public String uploadMovie(HttpServletRequest request,MultipartFile file) throws Exception {
		
		String fileName=file.getOriginalFilename();
		String uploadContentType =file.getContentType();
		if (file.getSize()> 1024 * 1024 * 50) { 
			throw new RuntimeException("文件大小不得大于50M");
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = "movie_"+df.format(new Date())+uploadContentType;
	    String uploadPath = request.getSession().getServletContext().getRealPath("/appmovie_movie")+File.separatorChar;   //设置保存目录  
	    File file1 =new File(uploadPath);    
        //如果文件夹不存在则创建    
        if  (!file1.exists()  && !file1 .isDirectory())      
        {       
            file1.mkdir();    
        }   
		
		file.transferTo(new File(uploadPath +fileName)); 
        return "/fr-here/appmovie_movie/"+fileName;  
	}
}

