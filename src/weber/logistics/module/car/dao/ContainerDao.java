package weber.logistics.module.car.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.car.model.Container;

public interface ContainerDao extends BaseDao<Container> {

	Container selectByCode(String code);
}
