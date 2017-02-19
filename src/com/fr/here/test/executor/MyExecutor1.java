package com.fr.here.test.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor1 extends Thread{
	private int index;
	public MyExecutor1(int i) {
		this.index = i;
	}
	public void run() {
		try {
			System.out.println("[" + this.index + "] start....");
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println("[" + this.index + "] end.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			//service.execute(new MyExecutor1(i));
			service.submit(new MyExecutor1(i));
		}
		System.out.println("submit finish");
		service.shutdown();
	}


}
