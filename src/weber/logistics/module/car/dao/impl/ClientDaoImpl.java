package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.ClientDao;
import weber.logistics.module.car.model.Client;

public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao {

	protected String mapper = "weber.car.dao.ClientDao.";

	@Override
	public Client selectByCode(String code) {
		return getSqlSession().selectOne(mapper + "selectByCode", code);
	}

}
