package weber.logistics.module.car.dao.impl;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.TripRouteDao;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;

public class TripRouteDaoImpl extends BaseDaoImpl<TripRoute> implements
		TripRouteDao {

	protected String mapper = "weber.car.dao.TripRouteDao.";

	@Override
	public List<TripRoute> selectByTrip(Long tripId) {
		return getSqlSession().selectList(mapper + "selectByTrip", tripId);
	}

	@Override
	public Integer countEarningByTrip(Trip trip) {
		return getSqlSession().selectOne(mapper + "countEarningByTrip", trip);
	}

	@Override
	public Integer countPaymentByTrip(Trip trip) {
		return getSqlSession().selectOne(mapper + "countPaymentByTrip", trip);
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
	public List<TripRoute> selectByTripIdAndStatus(Map params) {
		return getSqlSession().selectList(mapper + "selectByTripIdAndStatus",
				params);
	}

	@Override
	public int updateStatus(TripRoute tripRoute) {
		return this.getSqlSession().update(mapper + "updateStatus", tripRoute);
	}

	@Override
	public int deleteByTrip(Trip trip) {
		return this.getSqlSession().delete(mapper + "deleteByTrip", trip);
	}

	@Override
	public List<TripRoute> selectByClient(Client client) {
		return this.getSqlSession().selectList(mapper + "selectByClient",
				client);
	}

}
