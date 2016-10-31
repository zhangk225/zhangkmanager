package com.zhangkssh.externalservice.dao.impl;

import java.io.Serializable;

import com.zhangkssh.baseframe.hibernate.ZhangkBaseDaoImpl;
import com.zhangkssh.externalservice.dao.BaseDao;

public class BaseDaoImpl<T extends Serializable> extends ZhangkBaseDaoImpl<T>
implements BaseDao<T> {

}
