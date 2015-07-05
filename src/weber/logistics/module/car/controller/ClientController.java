package weber.logistics.module.car.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ObjectIdGenerator;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.service.ClientService;
import weber.logistics.module.common.service.CityService;
import weber.logistics.module.common.service.ProvinceService;

@Controller
@RequestMapping("/api")
public class ClientController {

	private static Logger logger = Logger.getLogger(ClientController.class);
	@Autowired
	private ClientService clientService;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("client") @Valid Client client, BindingResult result) {

		logger.debug(" client:" + client);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkProvinceId(client.getProvince().getId())
				|| !ParameterCheck.checkCityId(client.getCity().getId())) {
			logger.error("/client 错误的省份或城市!" + client.getProvince().getId()
					+ ";" + client.getCity().getId());
			throw new ParameterException("错误的省份或城市!");
		}

		client.setId(ObjectIdGenerator.generateString());
		client.setEntryDate(new Date());

		clientService.save(client);

		return new JsonMessage(1000, "添加成功", client);
	}

	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("client") @Valid Client client,
			BindingResult result,
			@PathVariable(value = "clientId") String clientId) {

		logger.debug(" client:" + client);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkProvinceId(client.getProvince().getId())
				|| !ParameterCheck.checkCityId(client.getCity().getId())) {
			logger.error("/client 错误的省份或城市!" + client.getProvince().getId()
					+ ";" + client.getCity().getId());
			throw new ParameterException("错误的省份或城市!");
		}

		if (!ParameterCheck.checkObjectId(clientId)) {
			throw new ParameterException("错误的客户信息!");
		}

		client.setId(clientId);
		clientService.update(client);

		return new JsonMessage(1000, "修改成功", client);
	}

	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "clientId") String clientId) {

		if (!ParameterCheck.checkObjectId(clientId)) {
			logger.warn("get 非法请求信息: clientId " + clientId);
			throw new ParameterException("非法请求信息!");
		}

		Client result = clientService.get(new Client(clientId));

		return new JsonMessage(1000, "修改成功", result);
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("name:" + name);
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		name = (name != null && !name.equals("")) ? name : null;

		Client client = new Client();
		client.setName(name);
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clients",
				new JsonPage(clientService.findByConditionPage(client,
						pageBound)));
		return map;
	}
}
