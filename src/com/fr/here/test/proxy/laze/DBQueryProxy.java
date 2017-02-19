package com.fr.here.test.proxy.laze;

public class DBQueryProxy implements IDBQuery{
	private DBQuery real = null;

	public String request() {
		// TODO Auto-generated method stub
		//在真正需要的时候才能创建真实对象，创建过程可能很慢
		if(real==null){
		real = new DBQuery();
		}
		//在多线程环境下，这里返回一个虚假类，类似于 Future 模式
		return real.request();
	}

	public String response() {
		// TODO Auto-generated method stub
		//在真正需要的时候才能创建真实对象，创建过程可能很慢
		if(real==null){
		real = new DBQuery();
		}
		//在多线程环境下，这里返回一个虚假类，类似于 Future 模式
		return real.response();
	}

}
