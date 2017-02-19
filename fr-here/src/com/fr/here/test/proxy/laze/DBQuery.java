package com.fr.here.test.proxy.laze;

public class DBQuery implements IDBQuery{
	
	public DBQuery(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String request() {
		// TODO Auto-generated method stub
		return  "request string";
	}

	public String response() {
		// TODO Auto-generated method stub
		return "response string";
	}

}
