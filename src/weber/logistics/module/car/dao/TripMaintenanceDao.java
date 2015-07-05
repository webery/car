package weber.logistics.module.car.dao;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripMaintenance;

public interface TripMaintenanceDao extends BaseDao<TripMaintenance> {

	Integer countMoneyByTrip(Map params);

	int updateStatusByTripMaintenance(TripMaintenance tripMaintenance);

	int updateStatusByTrip(Trip trip);

	int updateStatusWithFilterByTrip(Map params);

	List<TripMaintenance> selectByTripIdAndStatus(Map params);

	int deleteByTrip(Trip trip);

	List<TripMaintenance> selectByTrip(Trip trip);

}
