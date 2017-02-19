package com.fr.here.model.base;

import java.io.Serializable;
import java.util.List;

/**
  * @Description: 向app端返回数据专用
  * @Author: shli
  * @Create Date: 2016-5-6下午03:10:29
 */
@SuppressWarnings("unchecked")
public class ResponseJson<T> implements Serializable{

	private static final long serialVersionUID = -3924916010140337737L;
	private Result result =  new Result();
	private List<T> list;
	private T model;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * @return the model
	 */
	public T getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(T model) {
		this.model = model;
	}
	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
