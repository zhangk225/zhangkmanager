package com.zhangkssh.baseframe.hibernate;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;

public class Condition implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String field;
	
	protected Criterion c;//张凯峰扩展

	public void setC(Criterion c) {
		this.c = c;
	}

	public Criterion getC() {
		return c;
	}

	public String getField() {
		return field;
	}
	
	

}
