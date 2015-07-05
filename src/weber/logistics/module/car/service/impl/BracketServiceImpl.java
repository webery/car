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
import weber.logistics.module.car.model.Bracket;
import weber.logistics.module.car.service.BracketService;

@Service("bracketService")
@Transactional
public class BracketServiceImpl implements BracketService {

	@Autowired
	private BracketDao bracketDao;

	@Override
	@Transactional
	public int save(Bracket model) {

		if (bracketDao.selectByPlateNum(model.getPlateNum()) != null) {
			throw new BusinessException("输入的托架车牌已经存在!");
		}

		return bracketDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Bracket model) {
		return bracketDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Bracket model) {

		Bracket temp = bracketDao.selectByPlateNum(model.getPlateNum());
		if (temp != null && !model.getId().equals(temp.getId())) {
			throw new BusinessException("输入的托架车牌已经存在!");
		}
		return bracketDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Bracket get(Bracket model) {
		return bracketDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Bracket> findAll() {
		return bracketDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Bracket> findByCondition(Bracket model) {
		return bracketDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Bracket> findByConditionPage(Bracket model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plateNum", model.getPlateNum());
		return bracketDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public Bracket findByPlateNum(String plateNum) {
		return bracketDao.selectByPlateNum(plateNum);
	}

}
