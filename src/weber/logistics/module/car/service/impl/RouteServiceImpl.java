package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ObjectIdGenerator;
import weber.logistics.module.car.dao.RouteDao;
import weber.logistics.module.car.model.Route;
import weber.logistics.module.car.service.RouteService;
import weber.logistics.module.common.dao.CityDao;
import weber.logistics.module.common.model.City;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao routeDao;
	@Autowired
	private CityDao cityDao;

	@Override
	@Transactional
	public int save(Route model) {
		return routeDao.insert(model);
	}

	@Override
	@Transactional
	public int save(City start, City destination) {

		if (routeDao.selectByCity(new Route(start, destination)) != null) {
			throw new ParameterException("该路线已经存在!");
		}

		City city1 = cityDao.selectOne(start);
		City city2 = cityDao.selectOne(destination);

		if (city1 == null || city2 == null) {
			throw new ParameterException("选择的出发城市或者重点城市不存在!");
		}

		if (start.getId().equals(destination.getId())) {
			routeDao.insert(new Route(city1, city2, ObjectIdGenerator
					.generateString(), city1.getName() + "<-->"
					+ city2.getName()));
		} else {
			routeDao.insert(new Route(city2, city1, ObjectIdGenerator
					.generateString(), city2.getName() + "-->"
					+ city1.getName()));
			routeDao.insert(new Route(city1, city2, ObjectIdGenerator
					.generateString(), city1.getName() + "-->"
					+ city2.getName()));
		}

		return 1;
	}

	@Override
	@Transactional
	public int delete(Route model) {
		return routeDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Route model) {
		return routeDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Route get(Route model) {
		return routeDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Route> findAll() {
		return routeDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Route> findByCondition(Route model) {
		return routeDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Route> findByConditionPage(Route route, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityName", route.getName());
		return routeDao.selectByConditionPage(params, pageBound);
	}
}
