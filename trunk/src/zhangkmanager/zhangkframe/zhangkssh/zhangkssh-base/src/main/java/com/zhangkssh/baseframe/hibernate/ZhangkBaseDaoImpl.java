package com.zhangkssh.baseframe.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zhangkssh.baseframe.common.page.Pagination;
import com.zhangkssh.baseframe.common.util.MyBeanUtil;

/**
 * DAO基类。
 * 
 * 提供hql分页查询，example分页查询，拷贝更新等功能。
 * 
 * @author zhangkaifeng
 * 
 * @param <T>
 */
@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ZhangkBaseDaoImpl<T extends Serializable> implements
		ZhangkBaseDao<T> {
	protected Logger log = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T find_Load(Serializable id, boolean lock) {
		Assert.notNull(id);
		T entity = null;
		if (lock) {
			entity = (T) getSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(getPersistentClass(), id);
		}
		return entity;
	}

	@Override
	public T find_Load(Serializable id) {
		return find_Load(id, false);
	}

	@Override
	public T find_Get(Serializable id) {
		Assert.notNull(id);
		return (T) getSession().get(getPersistentClass(), id);
	}

	/**
	 * 按属性查找唯一对象.
	 */
	@Override
	public T find_ByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	@Override
	public List<T> list_All() {
		return findByCriteria();
	}

	@Override
	public List<T> list_All(OrderBy... orders) {
		Criteria crit = createCriteria();
		if (orders != null) {
			for (OrderBy order : orders) {
				crit.addOrder(order.getOrder());
			}
		}
		return crit.list();
	}

	@Override
	public List<T> list_ByEg(T eg, String... exclude) {
		return list_ByEg(eg, false, exclude);
	}

	@Override
	public List<T> list_ByEg(T eg, Condition[] conds, String... exclude) {
		return list_ByEg(eg, false, conds, exclude);
	}

	@Override
	public List<T> list_ByEg(T eg, boolean anyWhere, String... exclude) {
		return list_ByEg(eg, anyWhere, null, exclude);
	}

	@Override
	public List<T> list_ByEg(T eg, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Criteria crit = getCritByEg(eg, anyWhere, conds, exclude);
		return crit.list();
	}

	@Override
	public List<T> list_ByEg(T eg, Condition[] conds, int firstResult,
			int maxResult, String... exclude) {
		return list_ByEg(eg, false, conds, firstResult, maxResult, exclude);
	}

	@Override
	public List<T> list_ByEg(T eg, boolean anyWhere, int firstResult,
			int maxResult, String... exclude) {
		return list_ByEg(eg, anyWhere, null, firstResult, maxResult, exclude);
	}

	@Override
	public List<T> list_ByEg(T eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude) {
		Criteria crit = getCritByEg(eg, anyWhere, conds, exclude);
		crit.setFirstResult(firstResult);
		crit.setMaxResults(maxResult);
		return crit.list();
	}

	@Override
	public List<T> list_ByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	@Override
	public Pagination page_All(int pageNo, int pageSize, OrderBy... orders) {
		Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize, null,
				OrderBy.asOrders(orders));
	}

	@Override
	public Pagination page_ByEg(T eg, int pageNo, int pageSize,
			String... exclude) {
		return page_ByEg(eg, false, null, pageNo, pageSize, exclude);
	}

	@Override
	public Pagination page_ByEg(T eg, Condition[] conds, int pageNo,
			int pageSize, String... exclude) {
		return page_ByEg(eg, false, conds, pageNo, pageSize, exclude);
	}

	@Override
	public Pagination page_ByEg(T eg, boolean anyWhere, int pageNo,
			int pageSize, String... exclude) {
		return page_ByEg(eg, anyWhere, pageNo, pageSize, exclude);
	}

	@Override
	public Pagination page_ByEg(T eg, boolean anyWhere, Condition[] conds,
			int pageNo, int pageSize, String... exclude) {
		Order[] orderArr = null;
		Condition[] condArr = null;
		if (conds != null && conds.length > 0) {
			List<Order> orderList = new ArrayList<Order>();
			List<Condition> condList = new ArrayList<Condition>();
			for (Condition c : conds) {
				if (c instanceof OrderBy) {
					orderList.add(((OrderBy) c).getOrder());
				} else {
					condList.add(c);
				}
			}
			orderArr = new Order[orderList.size()];
			condArr = new Condition[condList.size()];
			orderArr = orderList.toArray(orderArr);
			condArr = condList.toArray(condArr);
		}
		Criteria crit = getCritByEg(eg, anyWhere, condArr, exclude);
		return findByCriteria(crit, pageNo, pageSize, null, orderArr);
	}

	@Override
	public Object update_ByUpdater(Updater updater) {
		ClassMetadata cm = getCmd(updater.getBean().getClass());
		if (cm == null) {
			throw new RuntimeException("所更新的对象没有映射或不是实体对象");
		}
		Object bean = updater.getBean();
		Object po = getSession().load(bean.getClass(),
				cm.getIdentifier(bean, EntityMode.POJO));
		updaterCopyToPersistentObject(updater, po);
		return po;
	}

	@Override
	public Object update_Default(Object entity) {
		return update_ByUpdater(Updater.create(entity));
	}

	@Override
	public T update_Update(T entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		return entity;
	}

	@Override
	public T save_Save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		return entity;
	}

	@Override
	public Object Save_SaveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public Object save_Merge(Object entity) {
		Assert.notNull(entity);
		return getSession().merge(entity);
	}

	@Override
	public void save_Refresh(Object entity) {
		getSession().refresh(entity);
	}

	@Override
	public T save_SaveAndRefresh(T entity) {
		save_Save(entity);
		save_Refresh(entity);
		return entity;
	}

	@Override
	public void delete_Delete(Object entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
	}

	@Override
	public T delete_DeleteById(Serializable id) {
		if (id == null) {
			return null;
		}
		T entity = find_Load(id);
		getSession().delete(entity);
		return entity;
	}

	@Override
	public List<T> delete_DeleteByIds(Serializable[] ids) {
		List<T> dts = new ArrayList<T>();
		T del = null;
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				del = delete_DeleteById(id);
				if (del != null) {
					dts.add(del);
				}
			}
		}
		return dts;
	}

	@Override
	public T sort_GetMax() {
		Criteria crit = createCriteria();
		int totalCount = 0;
		Number n = (Number) crit.setProjection(Projections.rowCount())
				.uniqueResult();
		if (n != null) {
			totalCount = n.intValue();
		}
		int move = totalCount - 1;
		if (move <= 0) {
			move = 0;
		}
		crit.setFirstResult(move);
		crit.setMaxResults(1);
		return (T) crit.uniqueResult();
	}

	@Override
	public T sort_GetMax(T eg, String... exclude) {
		return sort_GetMax(eg, false, null, exclude);
	}

	@Override
	public T sort_GetMax(T eg, Condition[] conds, String... exclude) {
		return sort_GetMax(eg, false, conds, exclude);
	}

	@Override
	public T sort_GetMax(T eg, boolean anyWhere, String... exclude) {
		return sort_GetMax(eg, anyWhere, null, exclude);
	}

	@Override
	public T sort_GetMax(T eg, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Order[] orderArr = null;
		Condition[] condArr = null;
		if (conds != null && conds.length > 0) {
			List<Order> orderList = new ArrayList<Order>();
			List<Condition> condList = new ArrayList<Condition>();
			for (Condition c : conds) {
				if (c instanceof OrderBy) {
					orderList.add(((OrderBy) c).getOrder());
				} else {
					condList.add(c);
				}
			}
			orderArr = new Order[orderList.size()];
			condArr = new Condition[condList.size()];
			orderArr = orderList.toArray(orderArr);
			condArr = condList.toArray(condArr);
		}
		Criteria crit = getCritByEg(eg, anyWhere, condArr, exclude);
		int totalCount = 0;
		Number n = (Number) crit.setProjection(Projections.rowCount())
				.uniqueResult();
		if (n != null) {
			totalCount = n.intValue();
		}
		int move = totalCount - 1;
		if (move <= 0) {
			move = 0;
		}
		crit = getCritByEg(eg, anyWhere, conds, exclude);
		crit.setFirstResult(move);
		crit.setMaxResults(1);

		return (T) crit.uniqueResult();
	}

	@Override
	public T sort_GetMove(Sort sort) {
		Criteria crit = createCriteria();
		int move = sort.getmove() - 1;
		if (move <= 0) {
			move = 0;
		}
		crit.setFirstResult(move);
		crit.setMaxResults(1);
		return (T) crit.uniqueResult();
	}

	@Override
	public T sort_GetMove(Sort sort, T eg, String... exclude) {
		return sort_GetMove(sort, eg, false, null, exclude);
	}

	@Override
	public T sort_GetMove(Sort sort, T eg, Condition[] conds, String... exclude) {
		return sort_GetMove(sort, eg, false, conds, exclude);
	}

	@Override
	public T sort_GetMove(Sort sort, T eg, boolean anyWhere, String... exclude) {
		return sort_GetMove(sort, eg, anyWhere, null, exclude);
	}

	@Override
	public T sort_GetMove(Sort sort, T eg, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Criteria crit = getCritByEg(eg, anyWhere, conds, exclude);
		int move = sort.getmove() - 1;
		if (move <= 0) {
			move = 0;
		}
		crit.setFirstResult(move);
		crit.setMaxResults(1);
		return (T) crit.uniqueResult();
	}

	@Override
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T createNewEntiey() {
		try {
			return getPersistentClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("不能创建实体对象："
					+ getPersistentClass().getName());
		}
	}

	@Override
	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	private ClassMetadata getCmd(Class clazz) {
		return (ClassMetadata) sessionFactory.getClassMetadata(clazz);
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 按Criterion查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	protected List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	protected Pagination findByCriteria(Criteria crit, int pageNo,
			int pageSize, Projection projection, Order... orders) {
		int totalCount = 0;
		Number n = (Number) crit.setProjection(Projections.rowCount())
				.uniqueResult();
		if (n != null) {
			totalCount = n.intValue();
		}
		if (totalCount < pageNo) {
			pageNo = 1;
		}
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		crit.setProjection(projection);
		if (projection == null) {
			crit.setResultTransformer(Criteria.ROOT_ENTITY);
		}
		if (orders != null) {
			for (Order order : orders) {
				crit.addOrder(order);
			}
		}
		crit.setFirstResult(p.getFirstResult());
		crit.setMaxResults(p.getPageSize());
		p.setList(crit.list());
		return p;
	}

	protected Criteria getCritByEg(T bean, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(bean);
		example.setPropertySelector(NOT_BLANK);
		if (anyWhere) {
			example.enableLike(MatchMode.ANYWHERE);
			example.ignoreCase();
		}
		for (String p : exclude) {
			example.excludeProperty(p);
		}
		crit.add(example);
		// 处理排序和is null字段
		if (conds != null) {
			for (Condition o : conds) {
				if (o instanceof OrderBy) {
					OrderBy order = (OrderBy) o;
					crit.addOrder(order.getOrder());
				} else if (o instanceof Nullable) {
					Nullable isNull = (Nullable) o;
					if (isNull.isNull()) {
						crit.add(Restrictions.isNull(isNull.getField()));
					} else {
						crit.add(Restrictions.isNotNull(isNull.getField()));
					}
				} else {// 张凯峰扩展
					// never
					if (o != null) {
						crit.add(o.getC());
					}
				}
			}
		}
		// 处理many to one查询
		ClassMetadata cm = getCmd(bean.getClass());
		String[] fieldNames = cm.getPropertyNames();
		for (String field : fieldNames) {
			Object o = cm.getPropertyValue(bean, field, EntityMode.POJO);
			if (o == null) {
				continue;
			}
			ClassMetadata subCm = getCmd(o.getClass());
			if (subCm == null) {
				continue;
			}
			Serializable id = subCm.getIdentifier(o, EntityMode.POJO);
			if (id != null) {
				Serializable idName = subCm.getIdentifierPropertyName();
				crit.add(Restrictions.eq(field + "." + idName, id));
			} else {
				crit.createCriteria(field).add(Example.create(o));
			}
		}
		return crit;
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * 将更新对象拷贝至实体对象，并处理many-to-one的更新。
	 * 
	 * @param updater
	 * @param po
	 */
	private void updaterCopyToPersistentObject(Updater updater, Object po) {
		Map map = MyBeanUtil.describe(updater.getBean());
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (!updater.isUpdate(name, value)) {
				continue;
			}
			if (value != null) {
				Class valueClass = value.getClass();
				ClassMetadata cm = getCmd(valueClass);
				if (cm != null) {
					Serializable vid = cm.getIdentifier(value, EntityMode.POJO);
					// 如果更新的many to one的对象的id为空，则将many to one设置为null。
					if (vid != null) {
						value = getSession().load(valueClass, vid);
					} else {
						value = null;
					}
				}
			}
			try {
				PropertyUtils.setProperty(po, name, value);
			} catch (Exception e) {
				// never
				log.warn("更新对象时，拷贝属性异常", e);
			}
		}
	}

	/**
	 * 通过count查询获得本次查询所能获得的对象总数.
	 * 
	 * @param finder
	 * @return
	 */
	protected int countQueryResult(Finder finder) {
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
		return ((Number) query.iterate().next()).intValue();
	}

	/**
	 * 通过sql查询 张凯峰
	 * 
	 * @param finder
	 * @return
	 */
	protected Query sqlQuery(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query;
	}

	/**
	 * 通过sql查询获得本次查询所能获得的对象总数. 张凯峰
	 * 
	 * @param finder
	 * @return
	 */
	protected int countQueryResult(String sql) {
		Query query = sqlQuery(sql);
		return ((Number) query.list().get(0)).intValue();
	}

	protected Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		List list = query.list();
		p.setList(list);
		return p;
	}

	// 张凯峰加，可以将总页数当参数传入
	protected Pagination find(Finder finder, int pageNo, int pageSize,
			int totalCount) {
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		List list = query.list();
		p.setList(list);
		return p;
	}

	protected List find(Finder finder) {
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(finder.getFirstResult());
		if (finder.getMaxResults() > 0) {
			query.setMaxResults(finder.getMaxResults());
		}
		List list = query.list();
		return list;
	}

	/**
	 * 通过count查询获得本次查询所能获得的对象总数.
	 * 
	 * @return page对象中的totalCount属性将赋值.
	 */
	protected int countQueryResult(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) MyBeanUtil
					.getFieldValue(impl, "orderEntries");
			MyBeanUtil.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			log.error("不可能抛出的异常:{}", e.getMessage());
		}
		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount())
				.uniqueResult();
		if (totalCount < 1) {
			return 0;
		}
		// 将之前的Projection和OrderBy条件重新设回去
		c.setProjection(projection);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			MyBeanUtil.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			log.error("不可能抛出的异常:{}", e.getMessage());
		}
		return totalCount;
	}

	private Class<T> persistentClass;

	public ZhangkBaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private static final NotBlankPropertySelector NOT_BLANK = new NotBlankPropertySelector();

	/**
	 * 不为空的EXAMPLE属性选择方式
	 * 
	 * @author liufang
	 * 
	 */
	static final class NotBlankPropertySelector implements PropertySelector {
		private static final long serialVersionUID = 1L;

		public boolean include(Object object, String property, Type type) {
			return object != null
					&& !(object instanceof String && StringUtils
							.isBlank((String) object));
		}
	}

}
