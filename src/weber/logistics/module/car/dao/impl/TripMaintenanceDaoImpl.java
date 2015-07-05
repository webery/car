package weber.logistics.module.car.dao.impl;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.TripMaintenanceDao;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripMaintenance;

public class TripMaintenanceDaoImpl extends BaseDaoImpl<TripMaintenance>
		implements TripMaintenanceDao {

	protected String mapper = "weber.car.dao.TripMaintenanceDao.";

	@Override
	public Integer countMoneyByTrip(Map params) {
		return getSqlSession().selectOne(mapper + "countEarningByTrip", params);
	}

	@Override
	public int updateStatusByTripMaintenance(TripMaintenance tripMaintenance) {
		return getSqlSession().update(mapper + "updateStatusByTripMaintenance",
				tripMaintenance);
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
	public List<TripMaintenance> selectByTripIdAndStatus(Map params) {
		return getSqlSession().selectList(mapper + "selectByTripIdAndStatus",
				params);
	}

	@Override
	public int deleteByTrip(Trip trip) {

		return getSqlSession().delete(mapper + "deleteByTrip", trip);
	}

	@Override
	public List<TripMaintenance> selectByTrip(Trip trip) {
		return this.getSqlSession().selectList(mapper + "selectByTrip", trip);
	}
}
