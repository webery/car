package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/bracket")
public class BracketViewController {
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/bracket/create_bracket";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/bracket/query_bracket";
	}

}
