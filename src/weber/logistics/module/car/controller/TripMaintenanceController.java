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
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripMaintenance;
import weber.logistics.module.car.service.TripMaintenanceService;
import weber.logistics.module.car.service.TripService;

@Controller
@RequestMapping("/api")
public class TripMaintenanceController {

	private static Logger logger = Logger
			.getLogger(TripMaintenanceController.class);

	@Autowired
	private TripMaintenanceService tripMaintenanceService;
	@Autowired
	private TripService tripService;

	/**
	 * Id和trip的id一样
	 * 
	 * @param tripMaintenance
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/tripMaintenance", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("tripMaintenance") @Valid TripMaintenance tripMaintenance,
			BindingResult result) {

		logger.debug("tripMaintenance:" + tripMaintenance);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (tripService.get(new Trip(tripMaintenance.getId())) == null) {
			logger.debug("选择的单不存在!");
			throw new BusinessException("选择的单不存在!");
		}

		tripMaintenance.setEntryDate(new Date());

		tripMaintenanceService.save(tripMaintenance);

		return new JsonMessage(1000, "添加成功", tripMaintenance);
	}

	@RequestMapping(value = "/tripMaintenance/{tripMaintenanceId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "tripMaintenanceId") Long tripMaintenanceId) {

		TripMaintenance tripMaintenance = new TripMaintenance(tripMaintenanceId);
		tripMaintenanceService.delete(tripMaintenance);

		return new JsonMessage(1000, "修改成功", tripMaintenanceId);
	}

	@RequestMapping(value = "/tripMaintenance/{tripMaintenanceId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("tripMaintenance") @Valid TripMaintenance tripMaintenance,
			BindingResult result,
			@PathVariable(value = "tripMaintenanceId") Long tripMaintenanceId) {

		logger.debug("tripMaintenance: " + tripMaintenance);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		tripMaintenance.setId(tripMaintenanceId);
		tripMaintenance.setEntryDate(new Date());
		tripMaintenanceService.update(tripMaintenance);

		return new JsonMessage(1000, "修改成功", tripMaintenance);
	}

	@RequestMapping(value = "/tripMaintenance/{tripMaintenanceId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "tripMaintenanceId") Long tripMaintenanceId) {

		TripMaintenance result = tripMaintenanceService
				.get(new TripMaintenance(tripMaintenanceId));

		return new JsonMessage(1000, "修改成功", result);
	}

	@RequestMapping(value = "/tripMaintenances", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		TripMaintenance tripMaintenance = new TripMaintenance();
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tripRoutes",
				new JsonPage(tripMaintenanceService.findByConditionPage(
						tripMaintenance, pageBound)));
		return map;
	}
	/*
	 * @RequestMapping(value = "/tripMaintenance/{tripMaintenanceId}/cancel",
	 * method = RequestMethod.PUT) public @ResponseBody JsonMessage cancel(
	 * 
	 * @PathVariable(value = "tripMaintenanceId") String tripMaintenanceId) {
	 * 
	 * if (!ParameterCheck.checkObjectId(tripMaintenanceId)) { throw new
	 * ParameterException("非法请求信息!"); }
	 * 
	 * TripMaintenance tripMaintenance = tripMaintenanceService .get(new
	 * TripMaintenance(tripMaintenanceId));
	 * 
	 * if (tripMaintenance == null) { throw new
	 * BusinessException("取消的维修记录不存在！"); } // 单提交成功后所有路线完成 if
	 * (tripMaintenance.getStatus() != 0) { throw new
	 * BusinessException("不能修改不在进行中的维修记录！"); }
	 * 
	 * tripMaintenanceService .cancel(new TripMaintenance(tripMaintenanceId,
	 * 2));
	 * 
	 * return JsonResponse.getJsonMessage(1000, "取消成功"); }
	 */
}
