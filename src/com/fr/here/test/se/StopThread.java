package com.fr.here.test.se;

public class StopThread {

	public static void main(String[] args) throws InterruptedException {
		try {
			Thread t = new Thread(new Runnable() {

				public synchronized void run() {
					for (int i = 0; i < 1000; i++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out
								.println(Thread.currentThread().getName() + i);
					}

				}
			});

			t.start();
			Thread.sleep(5000);
			t.stop();
		} catch (Throwable t) {
			System.out.println("Caught in main: " + t);
			t.printStackTrace();
		}
	}

}
