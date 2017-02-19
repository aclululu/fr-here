package com.fr.here.model;

import java.io.Serializable;

/**
  * @Description: 新闻栏目
  * @Author: shli
  * @Create Date: 2016-6-29下午03:08:30
 */
public class NewsColumn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -863444518781343504L;
	private int id;
	private String name;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	
}
