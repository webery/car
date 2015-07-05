package weber.logistics.module.car.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ObjectIdGenerator;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;
import weber.logistics.module.car.service.RouteService;
import weber.logistics.module.car.service.TripRouteService;
import weber.logistics.module.car.service.TripService;

@Controller
@RequestMapping("/api")
public class TripRouteController {

	private static Logger logger = Logger.getLogger(TripRouteController.class);

	@Autowired
	private TripRouteService tripRouteService;
	@Autowired
	private TripService tripService;
	@Autowired
	private RouteService routeService;

	@RequestMapping(value = "/tripRoute", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(

	@ModelAttribute("tripRoute") @Valid TripRoute tripRoute,
			BindingResult result) {

		logger.debug("tripRoute:" + tripRoute);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		tripRoute.setId(ObjectIdGenerator.generateString());
		tripRoute.setFinishDate(tripRoute.getStartDate());
		tripRoute.setEntryDate(new Date());

		tripRouteService.save(tripRoute);

		return new JsonMessage(1000, "添加成功", tripRoute);
	}

	/**
	 * 假删除
	 * 
	 * @param tripRouteId
	 * @return
	 */
	@RequestMapping(value = "/tripRoute/{tripRouteId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "tripRouteId") String tripRouteId) {

		if (!ParameterCheck.checkObjectId(tripRouteId)) {
			throw new ParameterException("非法请求信息!");
		}

		TripRoute tripRoute = new TripRoute(tripRouteId);
		tripRouteService.delete(tripRoute);

		return new JsonMessage(1000, "删除成功", tripRouteId);
	}

	@RequestMapping(value = "/tripRoute/{tripRouteId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("tripRoute") @Valid TripRoute tripRoute,
			BindingResult result,
			@PathVariable(value = "tripRouteId") String tripRouteId) {

		logger.debug("tripRoute:" + tripRoute);

		if (!ParameterCheck.checkObjectId(tripRouteId)) {
			throw new ParameterException("非法请求信息!");
		}

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		tripRoute.setId(tripRouteId);
		tripRouteService.update(tripRoute);

		return new JsonMessage(1000, "修改成功", tripRoute);
	}

	@RequestMapping(value = "/tripRoute/{tripRouteId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "tripRouteId") String tripRouteId) {

		if (!ParameterCheck.checkObjectId(tripRouteId)) {
			throw new ParameterException("非法请求信息!");
		}

		TripRoute result = tripRouteService.get(new TripRoute(tripRouteId));

		return new JsonMessage(1000, "修改成功", result);
	}

	@RequestMapping(value = "/tripRoutes", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "tripId", required = true) Long tripId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		TripRoute tripRoute = new TripRoute();
		tripRoute.setTrip(new Trip(tripId));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tripRoutes",
				new JsonPage(this.queryPage(tripRoute, page, limit)));
		return map;
	}

	private List<TripRoute> queryPage(TripRoute tripRoute, int page, int limit) {
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);//
		return tripRouteService.findByConditionPage(tripRoute, pageBound);
	}

	@RequestMapping(value = "/tripRoute/{tripRouteId}/cancel", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage cancel(
			@PathVariable(value = "tripRouteId") String tripRouteId) {

		if (!ParameterCheck.checkObjectId(tripRouteId)) {
			throw new ParameterException("非法请求信息!");
		}

		TripRoute tripRoute = new TripRoute(tripRouteId);
		tripRoute.setStatus(2);
		tripRouteService.cancel(tripRoute);

		return new JsonMessage(1000, "取消成功", tripRouteId);
	}
}
