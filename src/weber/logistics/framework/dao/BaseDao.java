package weber.logistics.framework.dao;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.page.domain.PageBounds;

public interface BaseDao<T> {

	int insert(T model);

	int delete(T model);

	int update(T model);

	T selectOne(T model);

	List<T> selectAll();

	List<T> selectByCondition(T model);

	List<T> selectByConditionPage(Map<String, Object> params,
			PageBounds pageBound);

}
