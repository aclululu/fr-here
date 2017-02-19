package com.fr.here.test.hashcode;

import java.util.HashSet;

public class Demo {
	public final static String CONSTANT = "11";
	public static void main(String[] args){
		
		HashSet<RectObject> set = new HashSet<RectObject>();
		RectObject r1 = new RectObject(3,3);
		RectObject r2 = new RectObject(5,5);
		RectObject r3 = new RectObject(3,3);
		set.add(r1);
		set.add(r2);
		set.add(r3);
		set.add(r1);
		
		try{
			int i = divice();
		}catch (Exception e) {
			//throw new RuntimeException("error");
		}
		
		
		System.out.println("size:"+set.size());
		System.out.println(r1.equals(r1));
		
		
		
		//	System.out.println(22&3);
	}
	public static int divice(){
		return 9/0;
	}
}