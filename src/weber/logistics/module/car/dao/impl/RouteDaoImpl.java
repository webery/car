package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.RouteDao;
import weber.logistics.module.car.model.Route;

public class RouteDaoImpl extends BaseDaoImpl<Route> implements RouteDao {

	protected String mapper = "weber.car.dao.RouteDao.";

	@Override
	public Route selectByCity(Route route) {
		return this.getSqlSession().selectOne(mapper + "selectByCity", route);
	}
}
