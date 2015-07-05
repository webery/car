package weber.logistics.module.car.service;

import java.util.List;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;

public interface TripRouteService extends BaseService<TripRoute> {

	List<TripRoute> findByTrip(Long tripId);

	Integer countEarningByTrip(Trip trip);

	/**
	 * 取消路线
	 * 
	 * @param tripRoute
	 * @return
	 */
	int cancel(TripRoute tripRoute);

	/**
	 * 根据单取消该单的所有路线
	 * 
	 * @param tripRoute
	 * @return
	 */
	int cancelByTrip(Trip trip);

	/**
	 * 根据年月查询所有客户的路线
	 * 
	 * @param yearMonth
	 * @return
	 */
	List<Client> findAllClientTripByYearMonth(String yearMonth);

}
