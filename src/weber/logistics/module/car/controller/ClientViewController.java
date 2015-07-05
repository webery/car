package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/client")
public class ClientViewController {

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/client/create_client";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/client/query_client";
	}

}
