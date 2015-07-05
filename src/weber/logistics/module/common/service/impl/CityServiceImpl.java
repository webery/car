package weber.logistics.module.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.common.dao.CityDao;
import weber.logistics.module.common.model.City;
import weber.logistics.module.common.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	@Transactional
	public int save(City model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(City model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int update(City model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public City get(City model) {
		return cityDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<City> findByProvinceId(Integer provinceId) {
		return cityDao.selectByProvinceId(provinceId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<City> findByCondition(City model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<City> findByConditionPage(City model, PageBounds pageBound) {
		// TODO Auto-generated method stub
		return null;
	}

}
