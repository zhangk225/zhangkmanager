package com.zhangkssh.frame.dao.impl;

import java.io.Serializable;

import com.zhangkssh.baseframe.common.page.Pagination;
import com.zhangkssh.baseframe.hibernate.Condition;
import com.zhangkssh.baseframe.hibernate.Nullable;
import com.zhangkssh.externalservice.dao.impl.BaseDaoImpl;
import com.zhangkssh.frame.dao.Frame_BaseDAO;
import com.zhangkssh.frame.util.Frame_Constants;

public class Frame_BaseDAOImpl<T extends Serializable> extends BaseDaoImpl<T>
		implements Frame_BaseDAO<T> {
	protected Condition getRemovedCondition() {
		return Nullable.isNull(Frame_Constants.SQLFILED_REMOVED);
	}

	@Override
	public Pagination page_ByEg(T eg, Condition[] conds, int pageNo,
			String... exclude) {
		return page_ByEg(eg, false, conds, pageNo, Frame_Constants.PAGE_NUMBER,
				exclude);
	}
}
