package com.zhangkssh.frame.dao;

import java.io.Serializable;

import com.zhangkssh.baseframe.common.page.Pagination;
import com.zhangkssh.baseframe.hibernate.Condition;
import com.zhangkssh.externalservice.dao.BaseDao;

public interface Frame_BaseDAO<T extends Serializable> extends BaseDao<T> {

	/**
	 * 获取分页(默认每页记录数)
	 * 
	 * @param eg
	 * @param conds
	 * @param pageNo
	 * @param exclude
	 * @return
	 */
	public Pagination page_ByEg(T eg, Condition[] conds, int pageNo,
			String... exclude);
}
