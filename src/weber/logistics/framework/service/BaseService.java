package weber.logistics.framework.service;

import java.util.List;

import weber.logistics.framework.page.domain.PageBounds;

public interface BaseService<T> {
	/**
	 * 插入一条数据
	 * 
	 * @param model
	 * @return
	 */
	int save(T model);

	/**
	 * 删除一条数据
	 * 
	 * @param model
	 * @return
	 */
	int delete(T model);

	/**
	 * 修改一条数据
	 * 
	 * @param model
	 * @return
	 */
	int update(T model);

	/**
	 * 获得一条数据
	 * 
	 * @param model
	 * @return
	 */
	T get(T model);

	/**
	 * 获得所有记录
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 条件查询记录(不分页)
	 * 
	 * @param model
	 * @return
	 */
	List<T> findByCondition(T model);

	/**
	 * 条件查询记录(分页)
	 * 
	 * @param model
	 * @return
	 */
	List<T> findByConditionPage(T model, PageBounds pageBound);
}
