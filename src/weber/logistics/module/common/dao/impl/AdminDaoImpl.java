package weber.logistics.module.common.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.common.dao.AdminDao;
import weber.logistics.module.common.model.Admin;

public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	protected String mapper = "weber.car.dao.AdminDao.";

	@Override
	public Admin selectByAccountAndPassword(Admin admin) {
		return getSqlSession().selectOne(mapper + "selectByAccountAndPassword",
				admin);
	}

	@Override
	public Admin selectByAccount(Admin admin) {
		return getSqlSession().selectOne(mapper + "selectByAccount", admin);
	}

}
