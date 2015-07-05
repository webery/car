package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Client;

public interface ClientDao extends BaseDao<Client> {
	Client selectByCode(String code);
}
