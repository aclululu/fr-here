package com.fr.here.test.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore extends Thread {
	private Semaphore position;
	private int id;

	public MySemaphore(int i, Semaphore s) {
		this.id = i;
		this.position = s;
	}

	public void run() {
		try {
			//有没有空厕所
			if (position.availablePermits() > 0) {
				System.out.println("顾客[" + this.id + "]进入厕所，有空位");
			}else {
				System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
			}
			//获取到空厕所了
			position.acquire();
			System.out.println("顾客[" + this.id + "]获得坑位");
			//使用中...
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println("顾客[" + this.id + "]使用完毕");
			//厕所使用完之后释放
			position.release();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService list = Executors.newCachedThreadPool();
		Semaphore position = new Semaphore(2);//只有两个厕所
		//有十个人
		for (int i = 0; i < 10; i++) {
			list.submit( new MySemaphore(i + 1, position));
		}
		list.shutdown();
		position.acquireUninterruptibly(0);
		System.out.println("使用完毕，需要清扫了");
		//position.release(2);
	}

}
