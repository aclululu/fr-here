package com.fr.here.model;

import java.io.Serializable;

/**
  * @Description:供需栏目 
  * @Author: shli
  * @Create Date: 2016-6-29下午03:18:02
 */
public class SupplyColumn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5183354224959650454L;
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
