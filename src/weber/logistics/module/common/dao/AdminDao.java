package weber.logistics.module.common.dao;

import weber.logistics.framework.dao.BaseDao;
import weber.logistics.module.common.model.Admin;

public interface AdminDao extends BaseDao<Admin> {

	Admin selectByAccountAndPassword(Admin admin);

	Admin selectByAccount(Admin admin);
}
