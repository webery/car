package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Client;

public interface ClientService extends BaseService<Client> {
	
	Client findByCode(String code);
}
