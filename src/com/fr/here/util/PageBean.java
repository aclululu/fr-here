package com.fr.here.util;

import java.util.List;

public class PageBean {
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	//计算总页数
	public static int counttotalPage(final int pageSize, final int totalCount){
		int toalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize + 1;
		return toalPage;
	}
	
	//计算当前页开始的记录
	public static int countOffset(final int pageSize, final int currentPage){
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}
	
	//计算当前第几页
	public static int countCurrentPage(int page){
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
	
	private int totalCount;			//总记录数
	private int totalPage;			//总页数
	private int pageSize = 20;		//每页记录数
	private int currentPage = 1;		//当前页数
	private List list;				//要返回的某一页的记录列表
}
