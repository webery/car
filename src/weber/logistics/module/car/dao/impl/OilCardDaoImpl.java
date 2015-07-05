package weber.logistics.module.car.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.car.dao.OilCardDao;
import weber.logistics.module.car.model.OilCard;

public class OilCardDaoImpl extends BaseDaoImpl<OilCard> implements OilCardDao {

	protected String mapper = "weber.car.dao.OilCardDao.";

	@Override
	public OilCard selectByCode(String code) {
		return getSqlSession().selectOne(mapper + "selectByCode", code);
	}

}
