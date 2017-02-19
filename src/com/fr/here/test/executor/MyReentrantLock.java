package com.fr.here.test.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock extends Thread {

	TestReentrantLock lock;
	private int id;

	public MyReentrantLock(int i, TestReentrantLock test) {
		this.id = i;
		this.lock = test;
	}

	public void run() {
		lock.print(id);
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		TestReentrantLock lock = new TestReentrantLock();
		for (int i = 0; i < 10; i++) {
			service.submit(new MyReentrantLock(i, lock));
		}
		service.shutdown();
	}

}

class TestReentrantLock {
	ReentrantLock lock = new ReentrantLock();
	public void print(int str) {
		 
		try {
			lock.lock();
			System.out.println(str + "获得");
			Thread.sleep((int) (Math.random() * 1000));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(str + "释放");
			lock.unlock();
		}
	}

}