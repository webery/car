package weber.logistics.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.common.dao.EmployeeDao;
import weber.logistics.module.common.model.Employee;
import weber.logistics.module.common.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public int save(Employee model) {

		if (employeeDao.selectOne(model) != null) {
			throw new BusinessException("该编号的员工已经存在!");
		}
		return employeeDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Employee model) {
		return employeeDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Employee model) {
		return employeeDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee get(Employee model) {
		return employeeDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return employeeDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findByCondition(Employee model) {
		return employeeDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findByConditionPage(Employee model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", model.getName());
		return employeeDao.selectByConditionPage(params, pageBound);
	}

}
