package weber.logistics.module.common.service;

import java.util.List;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.common.model.City;

public interface CityService extends BaseService<City> {

	List<City> findByProvinceId(Integer provinceId);
}
