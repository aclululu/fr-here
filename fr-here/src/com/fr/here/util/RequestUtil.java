/**
 * 
 */
package com.fr.here.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author tfj
 * 2014-8-22
 */
public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	private static final Base64 base64 = new Base64(true);
	public static final String LAST_PAGE = "com.alibaba.lastPage";
    public static final String REDIRECT_HOME = "/";
    
    
    /**
     * 获取当前Request对象.
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    public static HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }
    
    /**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     * @return 当前session对象或{@code null}.
     */
    public static HttpSession currentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.getRequest().getSession(false);
    }
    
    /**
     * 保存当前请求
     */
    public static void saveRequest() {
        HttpServletRequest request = currentRequest();
        request.getSession().setAttribute(LAST_PAGE, RequestUtil.hashRequestPage(request));
        logger.debug("save request for {}", request.getRequestURI());
    }
    
    /**
     * 加密请求页面
     * @param request
     * @return
     */
    public static  String hashRequestPage(HttpServletRequest request) {
        String reqUri = request.getRequestURI();
        String query = request.getQueryString();
        if (query != null) {
            reqUri += "?" + query;
        }
        String targetPage = null;
        try {
            targetPage = base64.encodeAsString(reqUri.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            //this does not happen
        }
        return targetPage;
    }
    
    /**
     * 取出之前保存的请求
     * @return
     */
    public static String retrieveSavedRequest() {
        HttpSession session = currentSession();
        if (session == null) {
            return REDIRECT_HOME;
        }
        String HashedlastPage = (String) session.getAttribute(LAST_PAGE);
        if (HashedlastPage == null) {
        	return REDIRECT_HOME;
        } else {
            return retrieve(HashedlastPage);
        }
    }

    /**
     * 解密请求的页面
     * @param targetPage
     * @return
     */
    public static String retrieve(String targetPage) {
        byte[] decode = base64.decode(targetPage);
        try {
            String requestUri = new String(decode, "UTF-8");
            int i = requestUri.indexOf("/", 1);
            return requestUri.substring(i);
        } catch (UnsupportedEncodingException ex) {
            //this does not happen
            return null;
        }
    }
}
