package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Bracket;

public interface BracketDao extends BaseDao<Bracket> {

	Bracket selectByPlateNum(String plateNum);
}
