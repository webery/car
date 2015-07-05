package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.TripMaintenanceDao;
import weber.logistics.module.car.model.TripMaintenance;
import weber.logistics.module.car.service.TripMaintenanceService;

@Service("tripMaintenanceService")
public class TripMaintenanceServiceImpl implements TripMaintenanceService {

	@Autowired
	private TripMaintenanceDao tripMaintenanceDao;

	@Override
	@Transactional
	public int save(TripMaintenance model) {
		return tripMaintenanceDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(TripMaintenance model) {
		return tripMaintenanceDao.delete(model);
	}

	@Override
	@Transactional
	public int update(TripMaintenance model) {

		// 1.不存在就插入
		TripMaintenance tripMaintenance = tripMaintenanceDao.selectOne(model);
		if (tripMaintenance == null) {
			return tripMaintenanceDao.insert(model);
		} else if (tripMaintenance.getStatus() == 1) {
			throw new BusinessException("不能修改已经完成单的信息!");
		}
		// 2.否则更新
		return tripMaintenanceDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public TripMaintenance get(TripMaintenance model) {
		return tripMaintenanceDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripMaintenance> findAll() {
		return tripMaintenanceDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripMaintenance> findByCondition(TripMaintenance model) {
		return tripMaintenanceDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripMaintenance> findByConditionPage(TripMaintenance model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return tripMaintenanceDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int cancel(TripMaintenance tripMaintenance) {
		return tripMaintenanceDao
				.updateStatusByTripMaintenance(tripMaintenance);
	}

	@Override
	public TripMaintenance getByTrip(Long tripId) {

		return tripMaintenanceDao.selectOne(new TripMaintenance(tripId));
	}

}
