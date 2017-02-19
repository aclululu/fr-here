package com.fr.here.test.reflect;

public class Person {
	
	public Person (int id,String name){
		this.id = id;
		this.name = name;
	}
	public Person(){}
	
	public int id;
	private String name;
	public String address;
	public String info;

	
	public  int add(int a,int b){
		return a +b;
	};

}
