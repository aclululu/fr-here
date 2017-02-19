package com.fr.here.util;

import java.util.HashMap;
import java.util.Map;

public class AjaxResult {
	/**
	 * @param message  提示信息
	 * @param code     错误信息为300   正确信息为200
	 * @return
	 */
	public static Map<String, Object> getMessageMap(String message,int statusCode){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("statusCode",statusCode);
		data.put("message",message);
		return data;
	}
	
	/**
	 * @param message  提示信息
	 * @param code     错误信息为300   正确信息为200
	 * @return
	 */
	public static Map<String, Object> getMessageMap(String message,int statusCode,String forwordUrl){
		Map<String,Object> data = getMessageMap(message,statusCode);
		data.put("forwardUrl",forwordUrl);
		return data;
	}
	
}
