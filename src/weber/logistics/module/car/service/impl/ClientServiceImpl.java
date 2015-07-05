package weber.logistics.module.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.dao.ClientDao;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.service.ClientService;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	@Transactional
	public int save(Client model) {
		if (clientDao.selectByCode(model.getCode()) != null) {
			throw new BusinessException("输入的客户编号已经存在!");
		}
		return clientDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Client model) {
		return clientDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Client model) {
		Client temp = clientDao.selectByCode(model.getCode());
		if (temp != null && !model.getId().equals(temp.getId())) {
			throw new BusinessException("输入的客户编号已经存在!");
		}
		return clientDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Client get(Client model) {
		return clientDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findByCondition(Client model) {
		return clientDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findByConditionPage(Client model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", model.getName());
		return clientDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findByCode(String code) {
		return clientDao.selectByCode(code);
	}

}
