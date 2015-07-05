package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.OilCardDao;
import weber.logistics.module.car.model.OilCard;
import weber.logistics.module.car.service.OilCardService;

@Service("oilCardService")
public class OilCardServiceImpl implements OilCardService {

	@Autowired
	private OilCardDao oilCardDao;

	@Override
	@Transactional
	public int save(OilCard model) {

		if (oilCardDao.selectByCode(model.getCode()) != null) {
			throw new BusinessException("输入的托架油卡已经存在！");
		}
		return oilCardDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(OilCard model) {
		return oilCardDao.delete(model);
	}

	@Override
	@Transactional
	public int update(OilCard model) {
		return oilCardDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public OilCard get(OilCard model) {
		OilCard oilCard = oilCardDao.selectOne(model);
		return oilCard;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCard> findAll() {
		return oilCardDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCard> findByCondition(OilCard model) {
		return oilCardDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OilCard> findByConditionPage(OilCard model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("code", model.getCode());

		return oilCardDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public OilCard findByCode(String code) {
		return oilCardDao.selectByCode(code);
	}

}
