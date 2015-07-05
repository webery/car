package weber.logistics.module.car.service;

import java.util.List;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.common.model.Employee;

public interface TripService extends BaseService<Trip> {

	Trip findByCode(String code);

	void finish(Trip trip);

	void cancel(Trip trip);

	List<Employee> print(String yearMonth);

	/**
	 * 按照年月查询所有emp的派单信息
	 * 
	 * @param yearMonth
	 * @return
	 */
	List<Employee> findAllEmpTripByYearMonth(String yearMonth);
}
