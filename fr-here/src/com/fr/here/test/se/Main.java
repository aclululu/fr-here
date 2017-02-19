package com.fr.here.test.se;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	public static void main(String[] args) {
		try {
			//初始时staticVar为5
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("result.obj"));
			out.writeObject(new Test("shli"));
			out.close();

			//序列化后修改为10

			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
					"result.obj"));
			Test t = (Test) oin.readObject();
			oin.close();
			
			
			//再读取，通过t.staticVar打印新的值
			System.out.println(t.getName());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
