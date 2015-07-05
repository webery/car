package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/trailer")
public class TrailerViewController {

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/trailer/create_trailer";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/trailer/query_trailer";
	}

	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public String bind() {

		return "car/trailer/bind_trailer";
	}
}
