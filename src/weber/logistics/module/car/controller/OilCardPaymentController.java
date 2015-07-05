package weber.logistics.module.car.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import weber.logistics.module.car.model.OilCardPayment;
import weber.logistics.module.car.service.OilCardPaymentService;

@Controller
@RequestMapping("/api")
public class OilCardPaymentController {

	private static Logger logger = Logger
			.getLogger(OilCardPaymentController.class);

	@Autowired
	private OilCardPaymentService oilCardPaymentService;

	@RequestMapping(value = "/oilCardPayment", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("oilCardPayment") @Valid OilCardPayment oilCardPayment,
			BindingResult result) {

		logger.debug("oilCardPayment:" + oilCardPayment);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		oilCardPayment.setId(ObjectIdGenerator.generateString());
		oilCardPayment.setEntryDate(new Date());

		oilCardPaymentService.save(oilCardPayment);

		return new JsonMessage(1000, "添加成功", oilCardPayment);
	}

	@RequestMapping(value = "/oilCardPayment/{oilCardPaymentId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "oilCardPaymentId") String oilCardPaymentId) {

		if (!ParameterCheck.checkObjectId(oilCardPaymentId)) {
			throw new ParameterException("非法请求信息!");
		}

		oilCardPaymentService.delete(new OilCardPayment(oilCardPaymentId));

		return new JsonMessage(1000, "删除成功", "");
	}

	@RequestMapping(value = "/oilCardPayment/{oilCardPaymentId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@PathVariable(value = "oilCardPaymentId") String oilCardPaymentId,
			@ModelAttribute("oilCardPayment") @Valid OilCardPayment oilCardPayment,
			BindingResult result) {

		logger.debug("oilCardPayment:" + oilCardPayment);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkObjectId(oilCardPaymentId)) {
			throw new ParameterException("非法请求信息!");
		}

		oilCardPaymentService.update(oilCardPayment);

		return new JsonMessage(1000, "修改成功", oilCardPayment);
	}

	@RequestMapping(value = "/oilCardPayment/{oilCardPaymentId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "oilCardPaymentId") String oilCardPaymentId) {

		if (!ParameterCheck.checkObjectId(oilCardPaymentId)) {
			throw new ParameterException("非法请求信息!");
		}

		OilCardPayment result = oilCardPaymentService.get(new OilCardPayment(
				oilCardPaymentId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/oilCardPayments", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionPage(
			@RequestParam(value = "yearMonth", required = false) String yearMonth,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;

		if (yearMonth != null) {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date printDate;
			try {
				printDate = sdf.parse(yearMonth);
				if (now.before(printDate)) {
					throw new ParameterException("打印时间不能早于当前时间!");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				throw new ParameterException("请输入yyyy-mm的日期格式!");
			}
		}
		OilCardPayment oilCardPayment = new OilCardPayment();

		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("oilCardPayments",
				new JsonPage(oilCardPaymentService.findByConditionPage(
						oilCardPayment, yearMonth, pageBound)));
		return map;
	}

}
