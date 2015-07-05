package weber.logistics.module.car.dao;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;

public interface TripRouteDao extends BaseDao<TripRoute> {

	List<TripRoute> selectByTrip(Long tripId);

	int updateStatusByTrip(Trip trip);

	int updateStatus(TripRoute tripRoute);

	Integer countEarningByTrip(Trip trip);

	Integer countPaymentByTrip(Trip trip);

	int updateStatusWithFilterByTrip(Map params);

	List<TripRoute> selectByTripIdAndStatus(Map params);

	int deleteByTrip(Trip trip);

	List<TripRoute> selectByClient(Client client);

}
