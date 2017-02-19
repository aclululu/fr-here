package com.fr.here.test.se;

/** 
 *  
其实Join方法实现是通过wait（小提示：Object 提供的方法）。 
 当main线程调用t.join时候，main线程会获得线程对象t的锁（wait意味着拿到该对象的锁), 
 调用该对象的wait(等待时间)，直到该对象唤醒main线程，比如退出后。 
这就意味着main 线程调用t.join时， 
必须能够拿到线程t对象的锁，如果拿不到它是无法wait的，刚开的例子t.join(1000)不是说明了main线程等待1秒， 
如果在它等待之前，其他线程获取了t对象的锁，它等待时间可不就是1秒了 
 * @author weijiang204321 
 * 
 */  
public class ThreadJoinTest {  
	public static int a = 0;
    public static void main(String[] args) {  
        Thread t = new Thread(new Runnable() {
			
			public void run() {
				for(int i=0;i<5;i++){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a++;
				}
			}
		});  
       // new ThreadTest(t).start();  
       // t.start();  
        t.start();
        try {
			t.join(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println(a);
    }  
}  
  

  
class ThreadTest extends Thread {  
    Thread thread;  
    public ThreadTest(Thread thread) {  
        this.thread = thread;  
    }  
  
    @Override  
    public void run() {  
        holdThreadLock();  
    }  
  
    public void holdThreadLock() {  
        //用当前的线程当做lock  
        synchronized (thread) {  
            System.out.println("getObjectLock");  
            try {  
                Thread.sleep(9*1000);  
            } catch (InterruptedException ex) {  
             ex.printStackTrace();  
            }  
            System.out.println("ReleaseObjectLock"); 
        }  
  
    }  
}  