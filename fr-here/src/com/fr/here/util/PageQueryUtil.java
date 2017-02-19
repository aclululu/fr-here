package com.fr.here.util;

public class PageQueryUtil {
	private static int PAGE_SIZE = 15;
	/**
	 * 对分页取数据做简单的处理
	 * @param pagesize  每页多少条记录
	 * @param pagenum   第几页   从0开始
	 * @return 返回一个数组 第零位 开始位置   第一位结束位置 
	 */
	public static int[] prePageQueryList(int pagesize,int pagenum){
		pagesize = pagesize>0 ? pagesize : PAGE_SIZE;
		pagenum = pagesize<0 ? 0 : pagenum;
		return new int[]{pagesize*pagenum,pagesize};
	}
}
