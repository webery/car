package weber.logistics.module.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.common.dao.ProvinceDao;
import weber.logistics.module.common.model.Province;
import weber.logistics.module.common.service.ProvinceService;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDao provinceDao;

	@Override
	@Transactional
	public int save(Province model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(Province model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int update(Province model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Province get(Province model) {
		return provinceDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Province> findAll() {
		return provinceDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Province> findByCondition(Province model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Province> findByConditionPage(Province model,
			PageBounds pageBound) {
		// TODO Auto-generated method stub
		return null;
	}

}
