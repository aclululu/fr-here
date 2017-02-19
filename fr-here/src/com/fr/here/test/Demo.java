package com.fr.here.test;

class Demo<T>  
{  
    public void method(T t)  
    {  
        System.out.println("method :"+ t);  
    }  
  
    public static <W> void staticMethod(W w)  
    {  
        System.out.println("staticMethod :"+ w);  
    }  
  
    public <K> void show( K  k)  
    {  
        System.out.println("Show :"+ k);  
    }  
  
    public <Q> void print(Q q)  
    {  
            System.out.println("Print :"+ q);  
    }  
}  
