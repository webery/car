package weber.logistics.module.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.common.dao.NationDao;
import weber.logistics.module.common.model.Nation;
import weber.logistics.module.common.service.NationService;

@Service("nationService")
public class NationServiceImpl implements NationService {

	@Autowired
	private NationDao nationDao;

	public NationDao getNationDao() {
		return nationDao;
	}

	public void setNationDao(NationDao nationDao) {
		this.nationDao = nationDao;
	}

	@Override
	@Transactional
	public int save(Nation model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(Nation model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int update(Nation model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Nation get(Nation model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nation> findAll() {
		return nationDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nation> findByCondition(Nation model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nation> findByConditionPage(Nation model, PageBounds pageBound) {
		// TODO Auto-generated method stub
		return null;
	}

}
