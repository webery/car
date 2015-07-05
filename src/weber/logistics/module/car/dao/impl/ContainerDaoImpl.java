package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.ContainerDao;
import weber.logistics.module.car.model.Container;

public class ContainerDaoImpl extends BaseDaoImpl<Container> implements
		ContainerDao {

	protected String mapper = "weber.car.dao.ContainerDao.";

	@Override
	public Container selectByCode(String code) {
		return getSqlSession().selectOne(mapper + "selectByCode", code);
	}

}
