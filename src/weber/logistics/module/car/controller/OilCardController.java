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

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.mvc.model.JsonResponse;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ObjectIdGenerator;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.OilCard;
import weber.logistics.module.car.service.OilCardService;

@Controller
@RequestMapping("/api")
public class OilCardController {

	private static Logger logger = Logger.getLogger(OilCardController.class);

	@Autowired
	private OilCardService oilCardService;

	@RequestMapping(value = "/oilcard", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("oilcard") @Valid OilCard oilcard,
			BindingResult result) {

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}
		oilcard.setId(ObjectIdGenerator.generateString());
		oilcard.setEntryDate(new Date());

		oilCardService.save(oilcard);

		logger.info("create 添加成功 " + oilcard);
		return JsonResponse.getJsonMessage(1000, "添加成功");
	}

	@RequestMapping(value = "/oilcard/{oilcardId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("oilcard") @Valid OilCard oilcard,
			@PathVariable(value = "oilcardId") String oilcardId,
			BindingResult result) {

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkObjectId(oilcardId)) {
			throw new ParameterException("非法请求信息!");
		}

		oilcard.setId(oilcardId);

		OilCard temp = oilCardService.get(oilcard);
		if (temp == null) {
			throw new BusinessException("油卡不存在!");
		}

		oilCardService.update(oilcard);

		logger.info("update 修改成功 " + oilcard);
		return JsonResponse.getJsonMessage(1000, "修改成功");
	}

	@RequestMapping(value = "/oilcard/{oilcardId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "oilcardId") String oilcardId) {

		if (!ParameterCheck.checkObjectId(oilcardId)) {
			throw new ParameterException("非法请求信息!");
		}

		OilCard result = oilCardService.get(new OilCard(oilcardId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/oilcards", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionPage(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("code:" + code);

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		code = (code != null && !code.equals("")) ? code : null;

		PageBounds pageBound = new PageBounds(page, limit);// 默认分页，返回PageList
		Map<String, Object> map = new HashMap<String, Object>();
		OilCard oilcard = new OilCard();
		oilcard.setCode(code);

		map.put("oilcards",
				new JsonPage(oilCardService.findByConditionPage(oilcard,
						pageBound)));
		return map;
	}

}
