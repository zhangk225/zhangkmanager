package com.zhangkssh.baseframe.common.page;

import java.util.List;

@SuppressWarnings({ "rawtypes" })
public class Pagination extends SimplePage implements java.io.Serializable,
		Paginable {

	private static final long serialVersionUID = 1L;

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	public Pagination(int pageNo, int pageSize, int totalCount, List list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
