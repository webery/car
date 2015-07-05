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
import weber.logistics.module.car.model.Trailer;
import weber.logistics.module.car.service.BracketService;
import weber.logistics.module.car.service.ContainerService;
import weber.logistics.module.car.service.TrailerService;
import weber.logistics.module.common.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class TrailerController {

	private static Logger logger = Logger.getLogger(TrailerController.class);

	@Autowired
	private TrailerService trailerService;
	@Autowired
	private ContainerService containerService;
	@Autowired
	private BracketService bracketService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/trailer", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("trailer") @Valid Trailer trailer,
			BindingResult result) {

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		trailer.setId(ObjectIdGenerator.generateString());
		trailer.setEntryDate(new Date());

		trailerService.save(trailer);

		return new JsonMessage(1000, "添加成功", trailer);
	}

	@RequestMapping(value = "/trailer/{trailerId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("trailer") @Valid Trailer trailer,
			BindingResult result,
			@PathVariable(value = "trailerId") String trailerId) {

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkObjectId(trailerId)) {
			throw new ParameterException("非法请求信息!");
		}

		trailerService.update(trailer);

		return new JsonMessage(1000, "修改成功", trailer);
	}

	@RequestMapping(value = "/trailer/{trailerId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "trailerId") String trailerId) {

		if (!ParameterCheck.checkObjectId(trailerId)) {
			logger.warn("get 非法请求信息! trailerId:" + trailerId);
			throw new ParameterException("非法请求信息!");
		}

		Trailer result = trailerService.get(new Trailer(trailerId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/trailers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "plateNum", required = false) String plateNum,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("plateNum:" + plateNum);
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		plateNum = (plateNum != null && !plateNum.equals("")) ? plateNum : null;
		Trailer trailer = new Trailer();
		trailer.setPlateNum(plateNum);

		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trailers",
				new JsonPage(trailerService.findByConditionPage(trailer,
						pageBound)));
		return map;

	}
}
