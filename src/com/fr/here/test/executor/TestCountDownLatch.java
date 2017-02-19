package com.fr.here.test.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {


	public static void main(String[] args) throws InterruptedException {
		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		// 结束的倒数锁
		final CountDownLatch end = new CountDownLatch(10);
		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						begin.await();// 一直阻塞
						//max  10s
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("No." + NO + " arrived");
					} catch (InterruptedException e) {
					} finally {
						//end.countDown();
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("Game Start");
		begin.countDown();
		
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0;i<10;i++)
					end.countDown();
			};
		}.start();
		end.await();
		
		System.out.println("Game Over");
		
		exec.shutdown();
	}
}