package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/container")
public class ContainerViewController {
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/container/create_container";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/container/query_container";
	}
}
