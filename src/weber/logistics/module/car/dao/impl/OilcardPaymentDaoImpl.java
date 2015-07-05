package weber.logistics.module.car.dao.impl;

import java.util.List;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.OilCardPaymentDao;
import weber.logistics.module.car.model.OilCardPayment;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;

public class OilcardPaymentDaoImpl extends BaseDaoImpl<OilCardPayment>
		implements OilCardPaymentDao {

	protected String mapper = "weber.car.dao.OilcardPaymentDao.";

	@Override
	public List<OilCardPayment> selectByTrip(Long tripId) {

		return this.getSqlSession().selectList(mapper + "selectByTrip", tripId);
	}

	@Override
	public Integer countMoneyByTrip(Trip trip) {
		return this.getSqlSession()
				.selectOne(mapper + "countMoneyByTrip", trip);
	}

	@Override
	public int deleteByTripRoute(TripRoute tripRoute) {
		return this.getSqlSession().delete(mapper + "deleteByTripRoute",
				tripRoute);
	}

	@Override
	public int deleteByTrip(Trip trip) {
		return this.getSqlSession().delete(mapper + "deleteByTrip", trip);
	}

	@Override
	public int updateStatusByTrip(Trip trip) {
		return this.getSqlSession().update(mapper + "updateStatusByTrip", trip);
	}

	@Override
	public Integer countMoneyByTripRoute(TripRoute tripRoute) {
		return this.getSqlSession().selectOne(mapper + "countMoneyByTripRoute",
				tripRoute);
	}

}
