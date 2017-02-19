package com.fr.here.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException  {
		Person person = new Person(1, "aclululu");
		
		//通过反射获取对象
		Class<?> clazz = Class.forName("com.fr.here.test.reflect.Person");
		//Constructor constructor = clazz.getConstructor(int.class,String.class);
		//Person person1 = (Person) constructor.newInstance(2,"shli");
		
		//Field field = person.getClass().getField("id");
		//Field field = Person.class.getField("id");
		Field id = clazz.getField("id");
		System.out.println(id.get(person));
		
		//通过反射获取私有属性  并且修改
		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true);
		name.set(person , "shli");
		System.out.println(name.get(person));
		
		//通过反射调用方法  静态方法可直接invoke Class
		Method method = clazz.getMethod("add", int.class,int.class);
		System.out.println(method.invoke(person,1,2));
		
		
		
		
		
		
		
	}
}
