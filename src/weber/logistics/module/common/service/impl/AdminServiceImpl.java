package weber.logistics.module.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.common.dao.AdminDao;
import weber.logistics.module.common.model.Admin;
import weber.logistics.module.common.service.AdminService;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional
	public int save(Admin model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(Admin model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int update(Admin model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Admin get(Admin model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> findByCondition(Admin model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admin> findByConditionPage(Admin model, PageBounds pageBound) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Admin findByAccountAndPassword(Admin admin) {
		return adminDao.selectByAccountAndPassword(admin);
	}

	@Override
	@Transactional(readOnly = true)
	public Admin findByAccount(Admin admin) {
		return adminDao.selectByAccount(admin);
	}

}
