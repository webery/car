package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Trailer;

public interface TrailerDao extends BaseDao<Trailer> {

	Trailer selectByPlateNum(String plateNum);

	int updateContAndBkAndEmp(Trailer trailer);

	Trailer selectByEmp(String empId);//一辆车可以有个司机

	Trailer selectByContainer(String containerId);

	Trailer selectByBracket(String bracketId);

}
