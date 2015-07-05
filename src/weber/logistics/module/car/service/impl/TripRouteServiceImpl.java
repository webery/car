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
import weber.logistics.module.car.dao.ClientDao;
import weber.logistics.module.car.dao.OilCardPaymentDao;
import weber.logistics.module.car.dao.TrailerDao;
import weber.logistics.module.car.dao.TripRouteDao;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.model.Trailer;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;
import weber.logistics.module.car.service.TripRouteService;

@Service("tripRouteService")
public class TripRouteServiceImpl implements TripRouteService {

	@Autowired
	private TripRouteDao tripRouteDao;
	@Autowired
	private OilCardPaymentDao oilcardPaymentDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private TrailerDao trailerDao;

	@Override
	@Transactional
	public int save(TripRoute model) {

		return tripRouteDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(TripRoute model) {
		// 1.删除单路线
		oilcardPaymentDao.deleteByTripRoute(model);
		// 2.删除充值
		return tripRouteDao.delete(model);
	}

	@Override
	@Transactional
	public int update(TripRoute model) {

		TripRoute temp = tripRouteDao.selectOne(model);
		if (temp == null) {
			throw new BusinessException("修改的单路线不存在！");
		}
		if (temp.getStatus() == 1) {
			throw new BusinessException("不能修改已经提交完成或者已经取消的单路线!");
		}

		return tripRouteDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public TripRoute get(TripRoute model) {
		return tripRouteDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripRoute> findAll() {
		return tripRouteDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripRoute> findByCondition(TripRoute model) {
		return tripRouteDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripRoute> findByConditionPage(TripRoute model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getTrip() != null) {
			params.put("tripId", model.getTrip().getId());
		}

		return tripRouteDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripRoute> findByTrip(Long tripId) {
		return tripRouteDao.selectByTrip(tripId);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer countEarningByTrip(Trip trip) {

		return tripRouteDao.countEarningByTrip(trip);
	}

	@Override
	@Transactional
	public int cancel(TripRoute tripRoute) {
		return tripRouteDao.updateStatus(tripRoute);
	}

	@Override
	@Transactional
	public int cancelByTrip(Trip trip) {
		return tripRouteDao.updateStatusByTrip(trip);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAllClientTripByYearMonth(String yearMonth) {

		List<Client> clients = clientDao.selectAll();
		Iterator<Client> it_c = clients.iterator();
		while (it_c.hasNext()) {
			Client client = it_c.next();
			List<TripRoute> tripRoutes = tripRouteDao.selectByClient(client);
			client.setTripRoutes(tripRoutes);
			Iterator<TripRoute> it_tr = tripRoutes.iterator();
			Trailer trailer = new Trailer();
			while (it_tr.hasNext()) {
				TripRoute tripRoute = it_tr.next();
				trailer.setId(tripRoute.getTrip().getTrailer().getId());
				tripRoute.setTrailer(trailerDao.selectOne(trailer));
				Integer oilPayment = oilcardPaymentDao
						.countMoneyByTripRoute(tripRoute);
				oilPayment = oilPayment != null ? oilPayment : 0;
				tripRoute.setOilPayment(oilPayment);// 客户在该路线的油卡充值金额
			}
		}

		return clients;
	}

}
