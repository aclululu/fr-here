package com.fr.here.test;


class TestDemo  
{  
    public static void main(String [] arags)  
    {  
        Demo<Double> d = new Demo<Double>();  
  
        d.method(3.4);  
          
        Demo.staticMethod(true);  
  
        d.show("haha");  
  
        d.print(new Integer(4));  
    }  
}  
