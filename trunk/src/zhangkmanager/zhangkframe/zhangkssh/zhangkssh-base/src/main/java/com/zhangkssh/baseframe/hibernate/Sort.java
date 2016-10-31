package com.zhangkssh.baseframe.hibernate;

public class Sort {
	public static final int SHANGYI = 1;// 上移
	public static final int XIAYI = 2;// 下移
	public static final int ZHENGXU = 1;// 正序
	public static final int DAOXU = 2;// 倒序
	private int page = 1;// 页数
	private int record = 0;// 每页记录数
	private int index = 1;// 索引
	private int sign = SHANGYI;// 1上移 2下移
	private int total = 0;// 总记录数
	private int i = DAOXU;// 排序方式 1正序 2倒序

	public int getmove() {
		int num = getNum();
		if (i == ZHENGXU) {
			if (sign == SHANGYI) {
				if (num > 1 && num <= total) {
					return num - 1;
				}
			} else if (sign == XIAYI) {
				if (num >= 1 && num < total) {
					return num + 1;
				}
			}
		} else if (i == DAOXU) {
			if (sign == XIAYI) {
				if (num >= 1 && num < total) {
					return num + 1;
				}
			} else if (sign == SHANGYI) {
				if (num > 1 && num <= total) {
					return num - 1;
				}
			}
		}
		return num;

	}

	public int getNum() {
		return ((page - 1) * record) + index;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
