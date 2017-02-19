package com.fr.here.test;

import java.io.Serializable;

public class Pojo  implements Serializable{
	private Integer id;
	private String name;
	public Work work;
	public Pojo(){
	}
	
	public Pojo(int id ,String name){
		
	}
	
	
@Override
public String toString() {
	return id + name;
}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
