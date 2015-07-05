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
import weber.logistics.framework.mvc.model.JsonMessage;

import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripMaintenance;
import weber.logistics.module.car.model.TripTraffic;
import weber.logistics.module.car.service.OilCardPaymentService;
import weber.logistics.module.car.service.TrailerService;
import weber.logistics.module.car.service.TripMaintenanceService;
import weber.logistics.module.car.service.TripRouteService;
import weber.logistics.module.car.service.TripService;
import weber.logistics.module.car.service.TripTrafficService;
import weber.logistics.module.common.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class TripController {

	private static Logger logger = Logger.getLogger(TripController.class);

	@Autowired
	private TripService tripService;
	@Autowired
	private TripRouteService tripRouteService;
	@Autowired
	private TripMaintenanceService tripMaintenanceService;
	@Autowired
	private TripTrafficService tripTrafficService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TrailerService trailerService;
	@Autowired
	private OilCardPaymentService oilCardPaymentService;

	@RequestMapping(value = "/trip", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(

	@ModelAttribute("trip") @Valid Trip trip, BindingResult result) {

		logger.debug(" trip:" + trip);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		trip.setFinishDate(trip.getStartDate());
		trip.setEntryDate(new Date());

		tripService.save(trip);

		return new JsonMessage(1000, "添加成功", trip);
	}

	@RequestMapping(value = "/trip/{tripId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("trip") @Valid Trip trip, BindingResult result,
			@PathVariable(value = "tripId") Long tripId) {

		logger.debug("update trip: " + trip);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		trip.setId(tripId);
		tripService.update(trip);

		return new JsonMessage(1000, "修改成功", trip);
	}

	@RequestMapping(value = "/trip/{tripId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "tripId") Long tripId) {
		if (tripId < 1) {
			return new JsonMessage(200, "输入信息格式错误", tripId);
		}
		tripService.delete(new Trip(tripId));

		return new JsonMessage(1000, "删除成功", "");
	}

	@RequestMapping(value = "/trip/{tripId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "tripId") Long tripId) {

		Trip result = tripService.get(new Trip(tripId));

		return new JsonMessage(1000, "查询成功", result);
	}

	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		Trip trip = new Trip();

		if ("going".equals(type)) {
			trip.setStatus(0);
		} else if ("finish".equals(type)) {
			trip.setStatus(1);
		} else if ("all".equals("all")) {

		} else {
			return null;// 未知操作
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trips", new JsonPage(this.queryPage(trip, page, limit)));
		return map;

	}

	@RequestMapping(value = "/trip/going", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryGoingPage(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		Trip trip = new Trip();
		trip.setStatus(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trips", new JsonPage(this.queryPage(trip, page, limit)));
		return map;

	}

	private List<Trip> queryPage(Trip trip, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);//

		return tripService.findByConditionPage(trip, pageBound);
	}

	@RequestMapping(value = "/trip/{tripId}/cancel", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage cancel(

	@PathVariable(value = "tripId") Long tripId) {

		// 该派单不在未完成状态,不能取消！
		// tripService.cancel(new Trip(tripId, 2));

		// return JsonResponse.getJsonMessage(1000, "取消成功");

		return null;
	}

	@RequestMapping(value = "/trip/{tripId}/status", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage finish(
			@PathVariable(value = "tripId") Long tripId,
			@RequestParam(value = "type", required = true) String type) {

		Trip trip = new Trip(tripId);

		if ("finish".equals(type)) {
			trip.setStatus(1);
		} else {
			return new JsonMessage(444, "未知操作", "");
		}

		tripService.finish(trip);

		return new JsonMessage(1000, "操作成功", "");
	}

	@RequestMapping(value = "/trip/{tripId}/tripMaintenance", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getTripMaintenance(
			@PathVariable(value = "tripId") Long tripId) {

		TripMaintenance tripMaintenance = tripMaintenanceService
				.getByTrip(tripId);

		return new JsonMessage(1000, "查询成功", tripMaintenance);
	}

	/**
	 * 根据单号获取违章(单号和维修记录id一样)
	 * 
	 * @param tripId
	 * @return
	 */
	@RequestMapping(value = "/trip/{tripId}/tripTraffic", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getTripTraffic(
			@PathVariable(value = "tripId") Long tripId) {

		TripTraffic tripTraffic = tripTrafficService.getByTrip(tripId);

		return new JsonMessage(1000, "查询成功", tripTraffic);
	}

	/**
	 * 根据派单的id查询该派单的所有路线
	 * 
	 * @param tripId
	 * @return
	 */
	@RequestMapping(value = "/trip/{tripId}/tripRoutes", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryTripRoute(
			@PathVariable(value = "tripId") Long tripId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tripRoutes", tripRouteService.findByTrip(tripId));

		return map;
	}

	/**
	 * 根据订单查询充值油卡
	 * 
	 * @param tripId
	 * @return
	 */
	@RequestMapping(value = "/trip/{tripId}/oilCardPayments", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryOilCardPayment(
			@PathVariable(value = "tripId") Long tripId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oilCardPayments", oilCardPaymentService.findByTrip(tripId));

		return map;
	}

}
