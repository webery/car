package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.TripTrafficDao;
import weber.logistics.module.car.model.TripTraffic;
import weber.logistics.module.car.service.TripTrafficService;

@Service("tripTrafficService")
public class TripTrafficServiceImpl implements TripTrafficService {

	@Autowired
	private TripTrafficDao tripTrafficDao;

	@Override
	@Transactional
	public int save(TripTraffic model) {
		return tripTrafficDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(TripTraffic model) {
		return tripTrafficDao.delete(model);
	}

	@Override
	@Transactional
	public int update(TripTraffic model) {

		TripTraffic tripTraffic = tripTrafficDao.selectOne(model);

		if (tripTraffic == null) {
			return tripTrafficDao.insert(model);
		} else if (tripTraffic.getStatus() == 1) {
			throw new BusinessException("不能修改已经完成单的信息!");
		}

		return tripTrafficDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public TripTraffic get(TripTraffic model) {
		return tripTrafficDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripTraffic> findAll() {
		return tripTrafficDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripTraffic> findByCondition(TripTraffic model) {
		return tripTrafficDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripTraffic> findByConditionPage(TripTraffic model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return tripTrafficDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int cancel(TripTraffic tripTraffic) {
		return tripTrafficDao.updateStatusByTripTraffic(tripTraffic);
	}

	@Override
	public TripTraffic getByTrip(Long tripId) {

		return tripTrafficDao.selectOne(new TripTraffic(tripId));
	}

}
