package com.fr.here.test.proxy.dynamic;

import com.fr.here.test.proxy.laze.IDBQuery;

public class Main {
	public static void main(String[] args){
		 Long now = System.currentTimeMillis();
		 //IDBQuery q = new DBQueryProxy(); //使用代里
		 IDBQuery q = DBQueryHandler.createProxy();  //不使用代理
		 //q.request(); //在真正使用时才创建真实对象
		 System.out.println( q.response());
		 
		 Long end = System.currentTimeMillis();
		 System.out.println(end - now);
		 
		 
		 }
}
