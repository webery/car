package weber.logistics.module.car.dao;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripTraffic;

public interface TripTrafficDao extends BaseDao<TripTraffic> {

	Integer countFineByTrip(Map params);

	int updateStatusByTripTraffic(TripTraffic tripTraffic);

	int updateStatusByTrip(Trip trip);
	
	int updateStatusWithFilterByTrip(Map params);
	
	List<TripTraffic> selectByTripIdAndStatus(Map params);
	
	int deleteByTrip(Trip trip);

}
