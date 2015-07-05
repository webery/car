package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")
public class OilCardViewController {

	@RequestMapping(value = "/oilcard/create", method = RequestMethod.GET)
	public String create() {

		return "car/oilcard/create_oilcard";
	}

	@RequestMapping(value = "/oilcard/query", method = RequestMethod.GET)
	public String query() {

		return "car/oilcard/query_oilcard";
	}

	@RequestMapping(value = "/oilCardPayment/printPayment", method = RequestMethod.GET)
	public String printPayment() {

		return "car/oilcard/print_payment";
	}

}
