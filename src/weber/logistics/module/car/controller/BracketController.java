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
import weber.logistics.framework.mvc.model.JsonResponse;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ObjectIdGenerator;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.Bracket;
import weber.logistics.module.car.service.BracketService;

@Controller
@RequestMapping("/api")
public class BracketController {

	private static Logger logger = Logger.getLogger(BracketController.class);

	@Autowired
	private BracketService bracketService;

	@RequestMapping(value = "/bracket", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("bracket") @Valid Bracket bracket,
			BindingResult result) {
		
		logger.debug(" bracket:" + bracket);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		bracket.setId(ObjectIdGenerator.generateString());
		bracket.setEntryDate(new Date());

		bracketService.save(bracket);

		logger.info("create 添加成功 " + bracket);
		return JsonResponse.getJsonMessage(1000, "添加成功");
	}

	@RequestMapping(value = "/bracket/{brackeId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("bracket") @Valid Bracket bracket,
			@PathVariable(value = "brackeId") String brackeId,
			BindingResult result) {

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkObjectId(brackeId)) {
			throw new ParameterException("非法请求信息!");
		}

		bracket.setId(brackeId);
		bracketService.update(bracket);

		return new JsonMessage(1000, "修改成功", bracket);
	}

	@RequestMapping(value = "/bracket/{brackeId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "brackeId") String brackeId) {

		if (!ParameterCheck.checkObjectId(brackeId)) {
			logger.warn("get-非法请求信息 brackeId:" + brackeId);
			throw new ParameterException("非法请求信息!");
		}

		Bracket result = bracketService.get(new Bracket(brackeId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/brackets", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "plateNum", required = false) String plateNum,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("plateNum:" + plateNum);
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		plateNum = (plateNum != null && !plateNum.equals("")) ? plateNum : null;

		PageBounds pageBound = new PageBounds(page, limit);// 默认分页，返回PageList
		Bracket bracket = new Bracket();
		bracket.setPlateNum(plateNum);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brackets",
				new JsonPage(bracketService.findByConditionPage(bracket,
						pageBound)));
		return map;
	}
}
