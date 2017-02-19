package com.fr.here.test.se;

public class TextThread {

	public static void main(String[] args) throws InterruptedException { 
	   TxtThread tt = new TxtThread(); 
	   Thread d = new Thread(tt); 
	   d.setPriority(2);
	   Thread d1 = new Thread(tt);
	   d1.setPriority(10);
	   d.start();
	   d1.setName("aclululu");
	   d1.start(); 
	   new Thread(tt).start(); 
	   new Thread(tt).start(); 
	   //d.suspend();
	   d.yield();
	   //Thread.sleep(200);
	   //d.resume();
	} 
	}

	class TxtThread implements Runnable { 
	int num = 10; 
	String str = new String();

	public synchronized void run() { 
		 System.out.println(Thread.currentThread().getName() );
	    while (num > 0) {
	     try { 
	      Thread.sleep(1000); 
	     } catch (Exception e) { 
	      e.getMessage(); 
	     } 
	     System.out.println(Thread.currentThread().getName() 
	       + "this is " + num--);
	     
	     if(num==5){
		    	try {
					wait(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		    
		    if(num==7){
		    	notify();
		    	try {
					wait(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
	     
	    } 
	} 
	}