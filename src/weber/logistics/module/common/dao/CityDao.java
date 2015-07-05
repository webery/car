package weber.logistics.module.common.dao;

import java.util.List;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.common.model.City;

public interface CityDao extends BaseDao<City> {

	public List<City> selectByProvinceId(Integer provinceId);
}
