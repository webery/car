package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.TrailerDao;
import weber.logistics.module.car.model.Trailer;

public class TrailerDaoImpl extends BaseDaoImpl<Trailer> implements TrailerDao {

	protected String mapper = "weber.car.dao.TrailerDao.";

	@Override
	public Trailer selectByPlateNum(String plateNum) {
		return getSqlSession().selectOne(mapper + "selectByCode", plateNum);
	}

	@Override
	public int updateContAndBkAndEmp(Trailer trailer) {
		return getSqlSession()
				.update(mapper + "updateContAndBkAndEmp", trailer);
	}

	@Override
	public Trailer selectByEmp(String empId) {
		return getSqlSession().selectOne(mapper + "selectByEmp", empId);
	}

	@Override
	public Trailer selectByContainer(String containerId) {
		return getSqlSession().selectOne(mapper + "selectByContainer",
				containerId);
	}

	@Override
	public Trailer selectByBracket(String bracketId) {
		return getSqlSession().selectOne(mapper + "selectByBracket", bracketId);
	}

}
