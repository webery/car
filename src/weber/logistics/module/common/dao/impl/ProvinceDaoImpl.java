package weber.logistics.module.common.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.common.dao.ProvinceDao;
import weber.logistics.module.common.model.Province;

public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements
		ProvinceDao {

	protected String mapper = "weber.common.dao.ProvinceDao.";

}
