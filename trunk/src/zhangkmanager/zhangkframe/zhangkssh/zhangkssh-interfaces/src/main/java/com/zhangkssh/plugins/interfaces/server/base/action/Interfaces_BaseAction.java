package com.zhangkssh.plugins.interfaces.server.base.action;

import com.zhangkssh.frame.action.Frame_Action;

public class Interfaces_BaseAction extends Frame_Action {

	private static final long serialVersionUID = 1L;

	protected String xmlPrts;

	protected String strs;

	public String getXmlPrts() {
		return xmlPrts;
	}

	public void setXmlPrts(String xmlPrts) {
		this.xmlPrts = xmlPrts;
	}

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

}
