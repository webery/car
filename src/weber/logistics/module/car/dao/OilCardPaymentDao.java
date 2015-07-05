package weber.logistics.module.car.dao;

import java.util.List;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.OilCardPayment;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;

public interface OilCardPaymentDao extends BaseDao<OilCardPayment> {

	List<OilCardPayment> selectByTrip(Long tripId);

	Integer countMoneyByTrip(Trip trip);

	int deleteByTripRoute(TripRoute tripRoute);

	int deleteByTrip(Trip trip);

	int updateStatusByTrip(Trip trip);

	/**
	 * 根据客户id和路线id统计油卡充值金额
	 * 
	 * @param tripRoute
	 * @return
	 */
	Integer countMoneyByTripRoute(TripRoute tripRoute);
}
