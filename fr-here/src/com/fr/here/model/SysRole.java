package com.fr.here.model;

import java.io.Serializable;
/**
  * @Description: 角色
  * @Author: shli
  * @Create Date: 2016-6-29下午02:56:57
 */
public class SysRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3113229597214602690L;
	
	private int id;
	private String name;
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
