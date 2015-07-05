package weber.logistics.module.car.dao.impl;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.TripTrafficDao;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripTraffic;

public class TripTrafficDaoImpl extends BaseDaoImpl<TripTraffic> implements
		TripTrafficDao {

	protected String mapper = "weber.car.dao.TripTrafficDao.";

	@Override
	public Integer countFineByTrip(Map params) {
		return getSqlSession().selectOne(mapper + "countFineByTrip", params);
	}

	@Override
	public int updateStatusByTripTraffic(TripTraffic tripTraffic) {
		return getSqlSession().update(mapper + "updateStatusByTripTraffic",
				tripTraffic);
	}

	@Override
	public int updateStatusByTrip(Trip trip) {
		return getSqlSession().update(mapper + "updateStatusByTrip", trip);
	}

	@Override
	public int updateStatusWithFilterByTrip(Map params) {
		return getSqlSession().update(mapper + "updateStatusWithFilterByTrip",
				params);
	}

	@Override
	public List<TripTraffic> selectByTripIdAndStatus(Map params) {
		return getSqlSession().selectList(mapper + "selectByTripIdAndStatus",
				params);
	}

	@Override
	public int deleteByTrip(Trip trip) {
		return this.getSqlSession().delete(mapper + "deleteByTrip", trip);
	}

}
