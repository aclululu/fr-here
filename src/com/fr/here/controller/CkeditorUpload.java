package com.fr.here.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CkeditorUpload {
	
	/*
	 *  图片命名格式
	 */
	private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMddHHmmss";
	/*
	 *  上传图片
	 */
	@RequestMapping(value = "/sys/cheditorupload")
	public void uplodaImg(@RequestParam("upload")MultipartFile file,//
			HttpServletRequest request, //
			HttpServletResponse response,//
			@RequestParam("CKEditorFuncNum")String CKEditorFuncNum)//
			throws IllegalStateException, IOException{
		
		PrintWriter out =response.getWriter();
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
            out.println("<script type=\"text/javascript\">");  
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");  
            out.println("</script>");  
            return ;  
        }  
		if (file.getSize()> 600 * 1024) {  
            out.println("<script type=\"text/javascript\">");  
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
                    + ",''," + "'文件大小不得大于600k');");  
            out.println("</script>");  
            return ;  
		}
		DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
		fileName = df.format(new Date())+expandedName;
	    String uploadPath = request.getSession().getServletContext().getRealPath("/ckeditor_img")+File.separatorChar;   //设置保存目录  
	    File file1 =new File(uploadPath);    
        //如果文件夹不存在则创建    
        if  (!file1.exists()  && !file1 .isDirectory())      
        {       
            file1.mkdir();    
        }   
		
		file.transferTo(new File(uploadPath +fileName));
        // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
        out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
                + ",'" + "/fr-here/ckeditor_img/" + fileName + "','')");  
        out.println("</script>");  
        return ;  
	}
}
