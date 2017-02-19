package com.fr.here.model.base;
import java.io.Serializable;


/**
 * 实体基类：实现序列化  方便对象的传输  消息的通知
 * @created 2015-4-15
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 6577555385975721292L;
	private String requltType;//请求结果类型（注意用于android访问请求时请求成功了，但在服务器端发生了异常，比如获取数据出错了的判断。值为空时表示请求数据成功了）
	private String errorMessage;//提示信息
	
	
	public String getRequltType() {
		return requltType;
	}
	public void setRequltType(String requltType) {
		this.requltType = requltType;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
