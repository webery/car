package weber.logistics.module.common.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.common.model.Admin;

public interface AdminService extends BaseService<Admin> {

	Admin findByAccountAndPassword(Admin admin);

	Admin findByAccount(Admin admin);
}
