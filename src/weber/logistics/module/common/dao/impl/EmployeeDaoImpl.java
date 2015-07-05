package weber.logistics.module.common.dao.impl;

import weber.logistics.framework.dao.BaseDaoImpl;
import weber.logistics.module.common.dao.EmployeeDao;
import weber.logistics.module.common.model.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements
		EmployeeDao {

	protected String mapper = "weber.common.dao.EmployeeDao.";

}
