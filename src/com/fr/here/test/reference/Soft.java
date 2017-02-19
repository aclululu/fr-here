package com.fr.here.test.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import com.fr.here.test.Pojo;

public class Soft {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, SoftReference<Pojo>> map = new HashMap<String, SoftReference<Pojo>>();
		Pojo pojo1 = new Pojo(1,"shli");
		Pojo pojo2 = new Pojo(2,"aclululu");
		
		SoftReference<Pojo> reference1= new SoftReference<Pojo>(pojo1);
		SoftReference<Pojo> reference2= new SoftReference<Pojo>(pojo2);
		
		pojo1 = null;
		pojo2 = null;
		
		map.put("1",reference1);
		map.put("2", reference2);
		
		System.gc();
		
		System.out.println(map.size());
		
		System.out.println(map.get("1").get().getClass());
		
		
		
		
		

	}

}
