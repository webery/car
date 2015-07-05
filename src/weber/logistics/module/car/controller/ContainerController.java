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
import weber.logistics.module.car.model.Container;
import weber.logistics.module.car.service.ContainerService;

@Controller
@RequestMapping("/api")
public class ContainerController {

	private static Logger logger = Logger.getLogger(ContainerController.class);

	@Autowired
	private ContainerService containerService;

	@RequestMapping(value = "/container", method = RequestMethod.POST)
	public @ResponseBody Object create(
			@ModelAttribute("container") @Valid Container container,
			BindingResult result) {

		logger.debug(" container:" + container);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		container.setId(ObjectIdGenerator.generateString());
		container.setEntryDate(new Date());

		containerService.save(container);

		return new JsonMessage(1000, "添加成功", container);
	}

	@RequestMapping(value = "/container/{containerId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("container") @Valid Container container,
			BindingResult result,
			@PathVariable(value = "containerId") String containerId) {

		logger.debug(" container:" + container);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		container.setId(containerId);

		containerService.update(container);

		return new JsonMessage(1000, "修改成功", container);
	}

	@RequestMapping(value = "/container/{containerId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "containerId") String containerId) {

		logger.debug(" containerId:" + containerId);

		if (!ParameterCheck.checkObjectId(containerId)) {
			throw new ParameterException("非法请求信息!");
		}

		Container result = containerService.get(new Container(containerId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/containers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		code = (code != null && !code.equals("")) ? code : null;

		Container container = new Container();
		container.setCode(code);

		PageBounds pageBound = new PageBounds(page, limit);// 默认分页，返回PageList
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("containers",
				new JsonPage(containerService.findByConditionPage(container,
						pageBound)));
		return map;
	}

}
