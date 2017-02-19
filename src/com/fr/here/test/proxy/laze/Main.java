package com.fr.here.test.proxy.laze;

public class Main {
	public static void main(String[] args){
		 Long now = System.currentTimeMillis();
		 //IDBQuery q = new DBQueryProxy(); //使用代里
		 IDBQuery q = new DBQuery();  //不使用代理
		 Long end = System.currentTimeMillis();
		 System.out.println(end - now);
		 
		 q.request(); //在真正使用时才创建真实对象
		 }
}
