package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Route;
import weber.logistics.module.common.model.City;

public interface RouteService extends BaseService<Route> {
	public int save(City start, City destination);
}
