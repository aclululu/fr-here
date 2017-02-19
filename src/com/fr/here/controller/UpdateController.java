package com.fr.here.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.persistence.FieldResult;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fr.here.model.News;
import com.fr.here.model.base.ResponseJson;

@Controller
public class UpdateController {
	private Logger log = Logger.getLogger(UpdateController.class);
	
	// ,method=RequestMethod.POST
	@ResponseBody
	 @RequestMapping(value = "/app/test/upload1")
	 public ResponseJson<String> update1(HttpServletRequest request,@RequestParam(value ="aFile",required=false)MultipartFile file){
		 log.error("进入上传方法");
		 ResponseJson<String> rj = new ResponseJson<String>();
		 String fileName=file.getOriginalFilename();
		 String uploadContentType =file.getContentType();
		 if (file.getSize()> 1024 * 1024 * 50) { 
				throw new RuntimeException("文件大小不得大于50M");
			}
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = "update_"+df.format(new Date())+fileName;
		    String uploadPath = request.getSession().getServletContext().getRealPath("/app_update")+File.separatorChar;   //设置保存目录  
		    File file1 =new File(uploadPath);    
	        //如果文件夹不存在则创建    
	        if  (!file1.exists()  && !file1 .isDirectory())      
	        {       
	            file1.mkdir();    
	        }   
			try {
				file.transferTo(new File(uploadPath +fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 rj.setModel("上传成功" + fileName);
		 return rj;
	 } 
	
	
	 @ResponseBody
	 @RequestMapping(value = "/app/test/upload2")
	 public ResponseJson<String> update2(HttpServletRequest request){
		 log.error("进入上传方法");
		 String fileName = "";
		 //创建一个通用的多部分解析器  
	     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		 ResponseJson<String> rj = new ResponseJson<String>();
		//判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){ 
	        	 //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();
	            String uploadPath = request.getSession().getServletContext().getRealPath("/app_update")+File.separatorChar;   //设置保存目录
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在 
	                    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        		   fileName = "update_"+df.format(new Date())+myFileName;
	        		    
	        			 File file1 = new File(uploadPath); 
	        			 //如果文件夹不存在则创建    
	        		        if  (!file1.exists()  && !file1 .isDirectory())      
	        		        {       
	        		            file1.mkdir();    
	        		        }
	                      try {
							file.transferTo(new File(uploadPath +fileName));
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
	                    
	                    }
	                }
	        }
		 rj.setModel("上传成功"+ fileName);
		 return rj;
	 }
	

}
