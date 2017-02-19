package com.fr.here.test.se;

public class TraditionalThreadCommunication {  
	  
    public static void main(String[] args) {  
        final Business business = new Business();  
        //子线程循环10次  
        new Thread(new Runnable(){  
            public void run() {  
                for(int i=0;i<50;i++){  
                    business.sub(i);  
                }  
            }  
        }).start();  
          
        //主线程循环100次  
        for(int i=0;i<50;i++){  
            business.main(i);  
        }  
          
    }  
      
    /** 
     * 业务逻辑类 
     * @author weijiang204321 
     * 
     */  
    static class Business{  
        private boolean bShouldSub = true;//true表示sub执行,false表示main执行  
        public synchronized void sub(int i){  
            //将这里的if改成while，效果更好，while会再次判断，安全性高  
            //因为有时候线程可能被假唤醒  
            while(!bShouldSub){  
                try {  
                    //等待  
                    this.wait();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
              
            for(int j=0;j<=10;j++){  
                System.out.println("sub thread sequece of" + j + ",loop of"+i);  
            }  
            bShouldSub = false;  
            this.notify();//唤醒该锁的等待线程  
        }  
          
        public synchronized void main(int i){  
            while(bShouldSub){  
                try {  
                    this.wait();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
            for(int j=0;j<=100;j++){  
                System.out.println("main thread sequece of" + j + ",loop of"+i);  
            }  
            bShouldSub = true;  
            this.notify();  
        }  
    }  
  
}  