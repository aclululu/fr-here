package com.fr.here.test.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.fr.here.test.proxy.laze.DBQuery;
import com.fr.here.test.proxy.laze.IDBQuery;

public class DBQueryHandler implements InvocationHandler{
	IDBQuery realQuery = null;//定义主题接口
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		//如果第一次调用，生成真实主题
		if(realQuery == null){
		realQuery = new DBQuery();
		}
		if(method.getName().equals("response"))
			//返回真实主题完成实际的操作
			return realQuery.response();
		else
			return realQuery.request();
	}
	
	
	public static IDBQuery createProxy(){
		/**
		  *  Proxy.getProxyClass(loader, interfaces).
	         getConstructor(new Class[] { InvocationHandler.class }).
	         newInstance(new Object[] { handler });

		 */
		
		IDBQuery proxy = (IDBQuery)Proxy.newProxyInstance(
		ClassLoader.getSystemClassLoader(), new Class[]{IDBQuery.class}, new DBQueryHandler()
		);
		return proxy;
		}

}
