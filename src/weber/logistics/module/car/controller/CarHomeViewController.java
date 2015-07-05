package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")
public class CarHomeViewController {

	@RequestMapping(method = RequestMethod.GET)
	public String home() {

		return "car/home";
	}

}
