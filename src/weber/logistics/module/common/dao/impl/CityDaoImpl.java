package weber.logistics.module.common.dao.impl;

import java.util.List;
import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.common.dao.CityDao;
import weber.logistics.module.common.model.City;

public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {

	protected String mapper = "weber.common.dao.CityDao.";

	@Override
	public List<City> selectByProvinceId(Integer provinceId) {
		return getSqlSession().selectList(mapper + "selectByProvinceId",
				provinceId);
	}

}
