package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.OilCardPaymentDao;
import weber.logistics.module.car.dao.TripDao;
import weber.logistics.module.car.dao.TripMaintenanceDao;
import weber.logistics.module.car.dao.TripRouteDao;
import weber.logistics.module.car.dao.TripTrafficDao;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripMaintenance;
import weber.logistics.module.car.model.TripTraffic;
import weber.logistics.module.car.service.TripService;
import weber.logistics.module.common.dao.EmployeeDao;
import weber.logistics.module.common.model.Employee;

@Service("tripService")
public class TripServiceImpl implements TripService {

	@Autowired
	private TripDao tripDao;
	@Autowired
	private TripRouteDao tripRouteDao;
	@Autowired
	private TripMaintenanceDao tripMaintenanceDao;
	@Autowired
	private TripTrafficDao tripTrafficDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private OilCardPaymentDao oilcardPaymentDao;

	@Override
	@Transactional
	public int save(Trip model) {
		return tripDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Trip model) {
		// 1.删除路线
		oilcardPaymentDao.deleteByTrip(model);
		// 2.
		tripMaintenanceDao.deleteByTrip(model);
		// 3.
		tripTrafficDao.deleteByTrip(model);
		// 4.
		tripRouteDao.deleteByTrip(model);
		// 5.
		return tripDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Trip model) {

		Trip temp = tripDao.selectOne(model);
		if (temp == null) {
			throw new BusinessException("修改的单不存在!");
		}
		if (temp.getStatus() == 1) {
			throw new BusinessException("不能修改已经提交完成或者已经取消的订单!");
		}

		return tripDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Trip get(Trip model) {
		Trip result = tripDao.selectOne(model);
		// 若查询的单不存在就不要执行下面的操作
		if (result == null) {
			return result;
		}
		// 1.总货款
		Integer countEarning = tripRouteDao.countEarningByTrip(result);
		countEarning = countEarning != null ? countEarning : 0;
		result.setEarning(countEarning);
		// 2.总已经支付
		Integer countPayment = tripRouteDao.countPaymentByTrip(result);
		countPayment = countPayment != null ? countPayment : 0;
		result.setPayment(countPayment);
		// 3.油卡总充值
		Integer countMoney = oilcardPaymentDao.countMoneyByTrip(result);
		countMoney = countMoney != null ? countMoney : 0;
		result.setOilPayment(countMoney);
		// 4.维修费用
		// 5.违章罚款

		/*
		 * // 1总收益 Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("tripId", result.getId()); params.put("goingStatus", 0);
		 * params.put("finishStatus", 1); =
		 * tripRouteDao.countEarningByTrip(params); countEarning = countEarning
		 * != null ? countEarning : 0; result.setEarning(countEarning); //
		 * 2.违章罚款 Integer countFine = tripTrafficDao.countFineByTrip(params);
		 * countFine = countFine != null ? countFine : 0;
		 * result.setTrafficTicket(countFine); // 3.维修费用 Integer countMoney =
		 * tripMaintenanceDao.countMoneyByTrip(params); countMoney = countMoney
		 * != null ? countMoney : 0; result.setMaintenanceCosts(countMoney);
		 */
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trip> findAll() {
		return tripDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trip> findByCondition(Trip model) {
		return tripDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trip> findByConditionPage(Trip model, PageBounds pageBound) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", model.getStatus());
		map.put("startDate", model.getStartDate());
		return tripDao.selectByConditionPage(map, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public Trip findByCode(String code) {
		return tripDao.selectByCode(code);
	}

	@Override
	@Transactional
	public void finish(Trip trip) {

		// 1.完成路线
		oilcardPaymentDao.updateStatusByTrip(trip);
		// 2.
		tripMaintenanceDao.updateStatusByTrip(trip);
		// 3.
		tripTrafficDao.updateStatusByTrip(trip);
		// 4.
		tripRouteDao.updateStatusByTrip(trip);
		// 5.
		tripDao.updateStatus(trip);

	}

	@Override
	@Transactional
	public void cancel(Trip trip) {
		// 全部取消
		// 1.
		tripDao.updateStatus(trip);
		// 2.
		tripRouteDao.updateStatusByTrip(trip);
		// 3.
		tripMaintenanceDao.updateStatusByTrip(trip);
		// 4.
		tripTrafficDao.updateStatusByTrip(trip);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> print(String yearMonth) {

		return null;
	}

	@Override
	public List<Employee> findAllEmpTripByYearMonth(String yearMonth) {

		List<Employee> employees = employeeDao.selectAll();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("yearMonth", yearMonth);
		Iterator<Employee> it_e = employees.iterator();

		while (it_e.hasNext()) {
			Employee employee = it_e.next();
			params.put("empId", employee.getId());
			List<Trip> trips = tripDao.selectByEmpAndYearMonth(params);
			employee.setTrips(trips);

			Iterator<Trip> it_t = trips.iterator();
			while (it_t.hasNext()) {
				Trip trip = it_t.next();
				trip.setTripRoutes(tripRouteDao.selectByTrip(trip.getId()));
				// 和Trip的id一样
				trip.setTripMaintenance(tripMaintenanceDao
						.selectOne(new TripMaintenance(trip.getId())));
				trip.setTripTraffic(tripTrafficDao.selectOne(new TripTraffic(
						trip.getId())));
			}

		}

		return employees;
	}
}
