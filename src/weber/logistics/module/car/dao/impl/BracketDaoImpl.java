package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.BracketDao;
import weber.logistics.module.car.model.Bracket;

public class BracketDaoImpl extends BaseDaoImpl<Bracket> implements BracketDao {

	protected String mapper = "weber.car.dao.BracketDao.";

	@Override
	public Bracket selectByPlateNum(String plateNum) {
		return getSqlSession().selectOne(mapper + "findByCode", plateNum);
	}
}
