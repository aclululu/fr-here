package com.fr.here.test.se;

import java.io.Serializable;

public class Test implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Test(String s){
		this.name  = s ;
	}
	private String name; 
	
	 public String getName() 
	 { 
		 return name; 
	 } 
	
	 public void setName(String name) 
	 { 
		 this.name = name; 
	 } 

}
