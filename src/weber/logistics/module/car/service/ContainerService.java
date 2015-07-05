package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Container;

public interface ContainerService extends BaseService<Container> {
	
	Container findByCode(String code);
}
