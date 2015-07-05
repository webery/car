package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.BracketDao;
import weber.logistics.module.car.dao.ContainerDao;
import weber.logistics.module.car.dao.TrailerDao;
import weber.logistics.module.car.model.Trailer;
import weber.logistics.module.car.service.TrailerService;
import weber.logistics.module.common.dao.EmployeeDao;

@Service("trailerService")
public class TrailerServiceImpl implements TrailerService {

	@Autowired
	private TrailerDao trailerDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ContainerDao containerDao;
	@Autowired
	private BracketDao bracketDao;

	@Override
	@Transactional
	public int save(Trailer model) {

		if (trailerDao.selectByPlateNum(model.getPlateNum()) != null) {
			throw new BusinessException("输入的拖车车牌已经存在!");
		}

		return trailerDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Trailer model) {
		return trailerDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Trailer model) {
		
		Trailer temp = trailerDao.selectByPlateNum(model.getPlateNum());
		if (temp != null && !model.getId().equals(temp.getId())) {
			throw new BusinessException("输入的拖车车牌已经存在!");
		}
		return trailerDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Trailer get(Trailer model) {
		return trailerDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trailer> findAll() {
		return trailerDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trailer> findByCondition(Trailer model) {
		return trailerDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Trailer> findByConditionPage(Trailer model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plateNum", model.getPlateNum());
		List<Trailer> trailers = trailerDao.selectByConditionPage(params,
				pageBound);
		return trailers;
	}

	@Override
	@Transactional(readOnly = true)
	public Trailer findByPlateNum(String plateNum) {
		return trailerDao.selectByPlateNum(plateNum);
	}

	@Override
	@Transactional(readOnly = true)
	public Trailer findByEmp(String empId) {
		return trailerDao.selectByEmp(empId);
	}

	@Override
	@Transactional(readOnly = true)
	public Trailer findByContainer(String containerId) {
		return trailerDao.selectByContainer(containerId);
	}

	@Override
	@Transactional(readOnly = true)
	public Trailer findByBracket(String bracketId) {
		return trailerDao.selectByBracket(bracketId);
	}

}
