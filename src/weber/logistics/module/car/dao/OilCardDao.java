package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.OilCard;

public interface OilCardDao extends BaseDao<OilCard> {

	public OilCard selectByCode(String code);
}
