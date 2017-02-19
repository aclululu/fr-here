package com.fr.here.test;

public class TestMain {
	public static void main(String[] args) {
		// 粗俗什么呢，故事那么悲伤。跟别人说起过去爱的人，却要装做最淡然的口吻，像是在说一个与自己无关的人，爱情不都是这样，你不也是这样
		int i = 3;
		int ii = i ^ 0xff;
		int iii = ii ^ 0xff;
		System.out.println(i);
		System.out.println(ii);
		System.out.println(iii);
		// 1111 1111 255 1 2 4 8 16 32 64 128
		// 0000 0011 ^
		// 1111 1100 255-3 = 252
		System.out.println(0xff);
	}
}
