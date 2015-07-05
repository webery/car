package weber.logistics.module.car.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.mvc.model.JsonResponse;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.Route;
import weber.logistics.module.car.service.RouteService;
import weber.logistics.module.common.model.City;
import weber.logistics.module.common.service.CityService;

@Controller
@RequestMapping("/api")
public class RouteController {

	@Autowired
	private RouteService routeService;
	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/route", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(@ModelAttribute("route") Route route) {

		if (!ParameterCheck.checkCityId(route.getStart().getId())
				|| !ParameterCheck.checkCityId(route.getDestination().getId())) {
			throw new ParameterException("输入错误的城市信息！");
		}

		City start = cityService.get(new City(route.getStart().getId()));
		City destination = cityService.get(new City(route.getDestination()
				.getId()));

		if (start == null || destination == null) {
			throw new ParameterException("选择的城市不存在！");
		}

		routeService.save(start, destination);

		return JsonResponse.getJsonMessage(1000, "添加成功!");
	}

	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionPage(

			@RequestParam(value = "cityName", required = false) String cityName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		PageBounds pageBound = new PageBounds(page, limit);// 默认分页，返回PageList
		Map<String, Object> map = new HashMap<String, Object>();

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		
		Route route = new Route();
		if (cityName != null && !cityName.equals("")) {
			route.setName(cityName);
		}

		map.put("routes",
				new JsonPage(routeService.findByConditionPage(route, pageBound)));
		return map;
	}
}
