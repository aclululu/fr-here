/**
 * 
 */
package com.fr.here.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fr.here.util.RequestUtil;
/**
 * @author shli
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	private static Logger log = Logger.getLogger(CommonInterceptor.class);
	/*
	 * 利用正则映射到需要拦截的路径    
    private String mappingURL;
    public void setMappingURL(String mappingURL) {    
               this.mappingURL = mappingURL;    
    }   
  */
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *     
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
    	log.debug("经过了perHandler方法");
    	if ("GET".equalsIgnoreCase(request.getMethod())) {
    		RequestUtil.saveRequest();
        }
    	List<String> list = new ArrayList<String>();
    	list.add("/rest/sys/login");
    	list.add("/rest/");
    	list.add("/rest/sys/");
    	list.add("/rest/sys");
    	
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		///rest/sys/user/login
        String username =  (String)request.getSession().getAttribute("user"); 
        if(!list.contains(url)&&username == null){
        	log.info("Interceptor：跳转到login页面！");
        	request.setAttribute("statusCode", 301);
        	request.setAttribute("message", "登录超时，请重新登录");
        	request.getRequestDispatcher("/WEB-INF/jsp/admin/dwz/ajaxDone.jsp").forward(request, response);
        	return false;
        }else
        	log.debug("经过了perHandler方法，返回true");
            return true;   
    }  
  
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception { 
    	log.debug("经过了postHandler方法");
        /*if(modelAndView != null){  //加入当前时间  
            modelAndView.addObject("var", "测试postHandle");  
        }  */
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception { 
    	log.debug("经过了afterCompletion方法");
    }  

}  
