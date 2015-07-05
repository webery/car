package weber.logistics.module.car.dao.impl;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.TripDao;
import weber.logistics.module.car.model.Trip;

public class TripDaoImpl extends BaseDaoImpl<Trip> implements TripDao {

	protected String mapper = "weber.car.dao.TripDao.";

	@Override
	public Trip selectByCode(String code) {
		return getSqlSession().selectOne(mapper + "selectOne", code);
	}

	@Override
	public int updateStatus(Trip model) {
		return getSqlSession().update(mapper + "updateStatus", model);
	}

	@Override
	public List<Trip> selectByEmpIdAndStatus(Map<String, Object> map) {
		return getSqlSession().selectList(mapper + "selectByEmpIdAndStatus",
				map);
	}

	@Override
	public List<Trip> selectByEmpAndYearMonth(Map<String, Object> map) {
		return getSqlSession().selectList(mapper + "selectByEmpAndYearMonth",
				map);
	}

}
