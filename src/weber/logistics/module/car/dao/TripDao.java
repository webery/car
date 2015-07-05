package weber.logistics.module.car.dao;

import java.util.List;
import java.util.Map;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Trip;

public interface TripDao extends BaseDao<Trip> {

	Trip selectByCode(String code);

	int updateStatus(Trip model);

	List<Trip> selectByEmpIdAndStatus(Map<String, Object> map);

	/**
	 * 根据员工编号和年月日查询派单
	 * 
	 * @param map
	 *            empId:yearMonth
	 * @return
	 */
	List<Trip> selectByEmpAndYearMonth(Map<String, Object> map);
}
