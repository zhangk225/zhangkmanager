package com.zhangkssh.baseframe.hibernate;

import java.io.Serializable;
import java.util.List;

import com.zhangkssh.baseframe.common.page.Pagination;

public interface ZhangkBaseDao<T extends Serializable> {
	/**
	 * 通过ID查找对象
	 * 
	 * @description 如果查找不到抛出异常
	 * 
	 * @param id
	 *            记录的ID
	 * @param lock
	 *            是否锁定对象
	 * @return 实体对象
	 * 
	 * @throws org.hibernate.ObjectNotFoundException
	 */
	public T find_Load(Serializable id, boolean lock);

	/**
	 * 通过ID查找对象,不锁定对象
	 * 
	 * @description 参考上面
	 * @param id
	 *            记录的ID
	 * @return 实体对象
	 * 
	 * @throws org.hibernate.ObjectNotFoundException
	 */
	public T find_Load(Serializable id);

	/**
	 * 通过id获取实体对象
	 * 
	 * @description 如果找不到返回null
	 * @param id
	 * 
	 * @return 实体对象
	 * 
	 */
	public T find_Get(Serializable id);

	/**
	 * 按属性查找唯一对象.
	 * 
	 * @description value为null会怎么样 property找不到会怎样
	 * 
	 * @param property
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 查找出的列表
	 */
	public T find_ByProperty(String property, Object value);

	/**
	 * 查找所有对象列表
	 * 
	 * @return 对象列表
	 */
	public List<T> list_All();

	/**
	 * 查找所有对象列表
	 * 
	 * @description 带排序
	 * 
	 * @param orders
	 *            排序参数
	 * @return
	 */
	public List<T> list_All(OrderBy... orders);

	/**
	 * 查找所有对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, String... exclude);

	/**
	 * 查找所有对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param conds
	 *            是否模糊查询，默认false。
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, Condition[] conds, String... exclude);

	/**
	 * 通过示例对象查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param anywhere
	 *            是否模糊查询，默认false。
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, boolean anywhere, String... exclude);

	/**
	 * 通过示例对象查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param conds
	 *            查询条件封装 排序和is null的字段。分别为OrderBy和String。
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, boolean anyWhere, Condition[] conds,
			String... exclude);

	/**
	 * firstResult maxResult没有研究清楚
	 * 
	 * @param eg
	 *            示例对象
	 * @param conds
	 *            查询条件封装 排序和is null的字段。分别为OrderBy和String。
	 * @param firstResult
	 * @param maxResult
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, Condition[] conds, int firstResult,
			int maxResult, String... exclude);

	/**
	 * firstResult maxResult没有研究清楚
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param firstResult
	 * @param maxResult
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, boolean anyWhere, int firstResult,
			int maxResult, String... exclude);

	/**
	 * firstResult maxResult没有研究清楚
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param conds
	 *            查询条件封装 排序和is null的字段。分别为OrderBy和String。
	 * @param firstResult
	 * @param maxResult
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<T> list_ByEg(T eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude);

	/**
	 * 按属性查找对象列表.
	 * 
	 * @description value为null会怎么样 property找不到会怎样
	 * 
	 * @param property
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 查找出的列表
	 */
	public List<T> list_ByProperty(String property, Object value);

	/**
	 * 分页查找对象列表
	 * 
	 * @param pageNo
	 *            当前页数
	 * 
	 * @param pageSize
	 *            每页记录数
	 * 
	 * @param orders
	 *            排序参数
	 * @return 分页对象
	 */
	public Pagination page_All(int pageNo, int pageSize, OrderBy... orders);

	/**
	 * 分页查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param exclude
	 *            需要排除的属性
	 * @return 分页对象
	 */
	public Pagination page_ByEg(T eg, int pageNo, int pageSize,
			String... exclude);

	/**
	 * 分页查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param conds
	 *            查询条件封装 排序和is null的字段。分别为OrderBy和String。
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param exclude
	 *            需要排除的属性
	 * @return 分页对象
	 */
	public Pagination page_ByEg(T eg, Condition[] conds, int pageNo,
			int pageSize, String... exclude);

	/**
	 * 分页查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param exclude
	 *            需要排除的属性
	 * @return 分页对象
	 */
	public Pagination page_ByEg(T eg, boolean anyWhere, int pageNo,
			int pageSize, String... exclude);

	/**
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param conds
	 *            查询条件封装 排序和is null的字段。分别为OrderBy和String。
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param exclude
	 *            需要排除的属性
	 * @return 分页对象
	 */
	public Pagination page_ByEg(T eg, boolean anyWhere, Condition[] conds,
			int pageNo, int pageSize, String... exclude);

	/**
	 * 根据Updater更新对象
	 * 
	 * @description 还不清楚怎么用
	 * @param updater
	 * @return 持久化对象
	 */
	public Object update_ByUpdater(Updater updater);

	/**
	 * 更新对象
	 * 
	 * @description 还不清楚怎么用
	 * @param entity
	 * @return
	 */
	public Object update_Default(Object entity);

	/**
	 * 更新对象
	 * 
	 * @description 不知道跟上面有啥区别
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public T update_Update(T entity);

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public T save_Save(T entity);

	/**
	 * 保存或更新对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public Object Save_SaveOrUpdate(Object entity);

	/**
	 * 保存或更新对象拷贝
	 * 
	 * @description 还不清楚怎么用
	 * @param entity
	 * @return 已更新的持久化对象
	 */
	public Object save_Merge(Object entity);

	/**
	 * 刷新对象
	 * 
	 * @description 还不清楚怎么用
	 * @param entity
	 */
	public void save_Refresh(Object entity);

	/**
	 * 还不清楚怎么用
	 * 
	 * @param entity
	 * @return
	 */
	public T save_SaveAndRefresh(T entity);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void delete_Delete(Object entity);

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 *            记录ID
	 */
	public T delete_DeleteById(Serializable id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> delete_DeleteByIds(Serializable[] ids);

	/**
	 * 获取排序后的最后一条记录
	 * 
	 * @return
	 */
	public T sort_GetMax();

	/**
	 * 排序用 得到排序后的最后一条记录
	 * 
	 * @param eg
	 * @param exclude
	 * @return
	 */
	public T sort_GetMax(T eg, String... exclude);

	/**
	 * 排序用 得到排序后的最后一条记录
	 * 
	 * @param eg
	 * @param conds
	 * @param exclude
	 * @return
	 */
	public T sort_GetMax(T eg, Condition[] conds, String... exclude);

	/**
	 * 排序用 得到排序后的最后一条记录
	 * 
	 * @param eg
	 * @param exclude
	 * @return
	 */
	public T sort_GetMax(T eg, boolean anyWhere, String... exclude);

	/**
	 * 排序用 得到排序后的最后一条记录
	 * 
	 * @param eg
	 * @param anyWhere
	 * @param conds
	 * @param exclude
	 * @return
	 */
	public T sort_GetMax(T eg, boolean anyWhere, Condition[] conds,
			String... exclude);

	/**
	 * 得到要移动的那条记录
	 * 
	 * @param sort
	 * @return
	 */
	public T sort_GetMove(Sort sort);

	/**
	 * 得到要移动的那条记录
	 * 
	 * @param sort
	 * @param eg
	 * @param exclude
	 * @return
	 */
	public T sort_GetMove(Sort sort, T eg, String... exclude);

	/**
	 * 得到要移动的那条记录
	 * 
	 * @param sort
	 * @param eg
	 * @param conds
	 * @param exclude
	 * @return
	 */
	public T sort_GetMove(Sort sort, T eg, Condition[] conds, String... exclude);

	/**
	 * 得到要移动的那条记录
	 * 
	 * @param sort
	 * @param eg
	 * @param anyWhere
	 * @param exclude
	 * @return
	 */
	public T sort_GetMove(Sort sort, T eg, boolean anyWhere, String... exclude);

	/**
	 * 得到要移动的那条记录
	 * 
	 * @param sort
	 * @param eg
	 * @param anyWhere
	 * @param conds
	 * @param exclude
	 * @return
	 */
	public T sort_GetMove(Sort sort, T eg, boolean anyWhere, Condition[] conds,
			String... exclude);

	/**
	 * 获得实体Class
	 * 
	 * @return
	 */
	public Class<T> getPersistentClass();

	/**
	 * 创建实体类的对象
	 * 
	 * @return
	 */
	public T createNewEntiey();

	/**
	 * 按属性查找对象的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value);
}
