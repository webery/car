package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/route")
public class RouteViewController {
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/route/create_route";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/route/query_route";
	}
	
}
