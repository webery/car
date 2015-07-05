package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Route;

public interface RouteDao extends BaseDao<Route> {

	/**
	 * 根据出发城市和目的城市查询路线
	 * 
	 * @param route
	 * @return
	 */
	Route selectByCity(Route route);
}
