package com.zhangkssh.frame.util.json;

import java.util.List;

import com.zhangkssh.baseframe.common.page.Pagination;

public class Frame_PageResponse<T> extends Frame_BaseResponse {

	private List<T> list;

	private int totalCount;

	private int totalPage;

	private int pageSize;

	private int prePage;

	private int nextPage;

	private int pageNo;

	private boolean firstPage;

	private boolean lastPage;

	public Frame_PageResponse(Pagination p) {
		setTotalCount(p.getTotalCount());
		setTotalPage(p.getTotalPage());
		setPageSize(p.getPageSize());
		setNextPage(p.getNextPage());
		setPrePage(p.getPrePage());
		setPageNo(p.getPageNo());
		setFirstPage(p.isFirstPage());
		setLastPage(p.isLastPage());
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

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

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
}
