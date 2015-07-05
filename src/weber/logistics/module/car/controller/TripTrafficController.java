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

import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.model.TripTraffic;
import weber.logistics.module.car.service.TripService;
import weber.logistics.module.car.service.TripTrafficService;

@Controller
@RequestMapping("/api")
public class TripTrafficController {
	private static Logger logger = Logger
			.getLogger(TripTrafficController.class);

	@Autowired
	private TripTrafficService tripTrafficService;

	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/tripTraffic", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(

	@ModelAttribute("tripTraffic") @Valid TripTraffic tripTraffic,
			BindingResult result) {

		logger.debug("tripTraffic:" + tripTraffic);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		tripTraffic.setEntryDate(new Date());
		tripTrafficService.save(tripTraffic);

		return new JsonMessage(1000, "添加成功", tripTraffic);
	}

	@RequestMapping(value = "/tripTraffic/{tripTrafficId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "tripTrafficId") Long tripTrafficId) {

		TripTraffic tripTraffic = new TripTraffic(tripTrafficId);
		tripTrafficService.delete(tripTraffic);

		return new JsonMessage(1000, "删除成功", tripTrafficId);
	}

	@RequestMapping(value = "/tripTraffic/{tripTrafficId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(

	@ModelAttribute("tripTraffic") @Valid TripTraffic tripTraffic,
			BindingResult result,
			@PathVariable(value = "tripTrafficId") Long tripTrafficId) {

		logger.debug("tripTraffic: " + tripTraffic);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		tripTraffic.setId(tripTrafficId);
		tripTraffic.setEntryDate(new Date());
		tripTrafficService.update(tripTraffic);

		return new JsonMessage(1000, "修改成功", tripTraffic);
	}

	@RequestMapping(value = "/tripTraffic/{tripTrafficId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "tripTrafficId") Long tripTrafficId) {

		TripTraffic result = tripTrafficService.get(new TripTraffic(
				tripTrafficId));

		return new JsonMessage(1000, "查询", result);
	}

	@RequestMapping(value = "/tripTraffics", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		TripTraffic tripTraffic = new TripTraffic();
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tripRoutes",
				new JsonPage(tripTrafficService.findByConditionPage(
						tripTraffic, pageBound)));
		return map;

	}

	/*
	 * @RequestMapping(value = "/tripTraffic/{tripTrafficId}/cancel", method =
	 * RequestMethod.PUT) public @ResponseBody JsonMessage cancel(
	 * 
	 * @PathVariable(value = "tripTrafficId") String tripTrafficId) {
	 * 
	 * if (!ParameterCheck.checkObjectId(tripTrafficId)) { throw new
	 * ParameterException("非法请求信息!"); }
	 * 
	 * TripTraffic tripTraffic = tripTrafficService.get(new TripTraffic(
	 * tripTrafficId));
	 * 
	 * if (tripTraffic == null) { throw new BusinessException("取消的违章记录不存在！"); }
	 * // 单提交成功后所有路线完成 if (tripTraffic.getStatus() != 0) { throw new
	 * BusinessException("不能修改不在进行中的违章记录！"); }
	 * 
	 * tripTrafficService.cancel(new TripTraffic(tripTrafficId, 2));
	 * 
	 * return JsonResponse.getJsonMessage(1000, "取消成功"); }
	 */
}
