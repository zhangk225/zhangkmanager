package com.zhangkssh.externalservice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.service.BaseService;

@Transactional
public class BaseServiceImpl implements BaseService {

	@Override
	public int other_CountStr(String str1, String str2) {
		return CommonUtil.other_CountStr(str1, str2);
	}

	@Override
	public <M, T> List<T> other_MapValue2List(Map<M, T> map) {
		return CommonUtil.other_MapValue2List(map);
	}
}
