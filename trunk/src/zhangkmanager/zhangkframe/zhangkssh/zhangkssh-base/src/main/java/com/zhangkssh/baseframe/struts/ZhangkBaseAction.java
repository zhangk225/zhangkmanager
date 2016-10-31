package com.zhangkssh.baseframe.struts;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.ValidationAwareSupport;
import com.zhangkssh.baseframe.common.page.Pagination;
import com.zhangkssh.baseframe.hibernate.Sort;

@SuppressWarnings({ "serial" })
public class ZhangkBaseAction implements Action, java.io.Serializable, Validateable,
		ValidationAware, ServletRequestAware, ServletResponseAware {
	private final ValidationAwareSupport validationAware = new ValidationAwareSupport();
	protected String[] ids;
	protected String id;
	protected Pagination pagination;
	protected Sort sort;
	protected Map<String, Object> jsonMap = new HashMap<String, Object>();
	protected int pageNo = 1;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public static final String LIST = "list";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String SHOW = "show";
	public static final String SELECT = "select";
	public static final String QUERY = "query";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String INDEX = "index";
	public static final String MAIN = "main";
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String PAIXU = "paixu";

	/**
	 * 验证批量操作
	 * 
	 * @return
	 */
	protected boolean vldBatch() {
		if (id == null && (ids == null || ids.length <= 0)) {
			addActionError("ID不能为空");
			return true;
		} else {
			if (id != null) {
				ids = new String[] { id };
			}
		}
		return false;
	}

	/**
	 * 绕过Template,直接输出内容的简便函数.
	 */
	protected String render(String text, String contentType) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType);
		response.getWriter().write(text);
		return null;
	}

	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text) throws IOException {
		return render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出字符串GBK编码.
	 */
	protected String renderHtmlGBK(String html) throws IOException {
		return render(html, "text/html;charset=GBK");
	}

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml) throws IOException {
		return render(xml, "text/xml;charset=UTF-8");
	}

	public String main() throws Exception {
		return MAIN;
	}

	public String change() throws Exception {
		return EDIT;
	}

	public String add() throws Exception {
		return ADD;
	}

	public String select() throws Exception {
		return SELECT;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setActionErrors(Collection errorMessages) {
		validationAware.setActionErrors(errorMessages);
	}

	public Collection getActionErrors() {
		return validationAware.getActionErrors();
	}

	public void setActionMessages(Collection messages) {
		validationAware.setActionMessages(messages);
	}

	public Collection getActionMessages() {
		return validationAware.getActionMessages();
	}

	public void setFieldErrors(Map errorMap) {
		validationAware.setFieldErrors(errorMap);
	}

	public Map getFieldErrors() {
		return validationAware.getFieldErrors();
	}

	public boolean hasActionErrors() {
		return validationAware.hasActionErrors();
	}

	public boolean hasActionMessages() {
		return validationAware.hasActionMessages();
	}

	public boolean hasErrors() {
		return validationAware.hasErrors();
	}

	public boolean hasFieldErrors() {
		return validationAware.hasFieldErrors();
	}

	public void addActionError(String anErrorMessage) {
		validationAware.addActionError(anErrorMessage);
	}

	public void addActionMessage(String aMessage) {
		validationAware.addActionMessage(aMessage);
	}

	public void addFieldError(String fieldName, String errorMessage) {
		validationAware.addFieldError(fieldName, errorMessage);
	}

	public void validate() {
		jsonMap.clear();
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

}
