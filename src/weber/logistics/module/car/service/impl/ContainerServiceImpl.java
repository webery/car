package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.ContainerDao;
import weber.logistics.module.car.model.Container;
import weber.logistics.module.car.service.ContainerService;

@Service("containerService")
@Transactional
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	private ContainerDao containerDao;

	@Override
	@Transactional
	public int save(Container model) {

		if (containerDao.selectByCode(model.getCode()) != null) {
			throw new BusinessException("输入的货柜编号已经存在！");
		}

		return containerDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Container model) {
		return containerDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Container model) {

		Container temp = containerDao.selectByCode(model.getCode());
		if (temp != null && !model.getId().equals(temp.getId())) {
			throw new BusinessException("输入的货柜编号已经存在！");
		}

		return containerDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Container get(Container model) {
		return containerDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Container> findAll() {
		return containerDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Container> findByCondition(Container model) {
		return containerDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Container> findByConditionPage(Container model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", model.getCode());
		return containerDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public Container findByCode(String code) {
		return containerDao.selectByCode(code);
	}

}
