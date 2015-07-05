package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.OilCardDao;
import weber.logistics.module.car.dao.OilCardPaymentDao;
import weber.logistics.module.car.dao.TripDao;
import weber.logistics.module.car.dao.TripRouteDao;
import weber.logistics.module.car.model.OilCardPayment;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;
import weber.logistics.module.car.service.OilCardPaymentService;

@Service("oilcardPaymentService")
@Transactional
public class OilCardPaymentServiceImpl implements OilCardPaymentService {

	@Autowired
	private OilCardPaymentDao oilcardPaymentDao;
	@Autowired
	private OilCardDao oilCardDao;
	@Autowired
	private TripDao tripDao;
	@Autowired
	private TripRouteDao tripRouteDao;

	@Override
	@Transactional
	public int save(OilCardPayment model) {

		TripRoute tripRoute = tripRouteDao.selectOne(model.getTripRoute());

		if (tripRoute == null) {
			throw new BusinessException("找不到该派单的路线!");
		}
		// 设置路线
		model.setRoute(tripRoute.getRoute());
		Trip trip = tripDao.selectOne(tripRoute.getTrip());
		// 设置路线
		model.setTrip(trip);
		// 设置油卡
		model.setOilCard(trip.getOilcard());

		return oilcardPaymentDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(OilCardPayment model) {

		return oilcardPaymentDao.delete(model);
	}

	@Override
	@Transactional
	public int update(OilCardPayment model) {

		return oilcardPaymentDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public OilCardPayment get(OilCardPayment model) {

		return oilcardPaymentDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCardPayment> findAll() {

		return oilcardPaymentDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCardPayment> findByCondition(OilCardPayment model) {

		return oilcardPaymentDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCardPayment> findByConditionPage(OilCardPayment model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return oilcardPaymentDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCardPayment> findByTrip(Long tripId) {

		return oilcardPaymentDao.selectByTrip(tripId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCardPayment> findByConditionPage(
			OilCardPayment oilCardPayment, String yearMonth,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("yearMonth", yearMonth);
		if (oilCardPayment.getClient() != null) {
			params.put("client", oilCardPayment.getClient().getId());
		}
		if (oilCardPayment.getOilCard() != null) {
			params.put("oilcard", oilCardPayment.getOilCard().getId());
		}

		return oilcardPaymentDao.selectByConditionPage(params, pageBound);
	}
}
