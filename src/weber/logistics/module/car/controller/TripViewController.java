package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")
public class TripViewController {

	@RequestMapping(value = "/trip/create", method = RequestMethod.GET)
	public String create() {

		return "car/trip/create_trip";
	}

	@RequestMapping(value = "/trip/query", method = RequestMethod.GET)
	public String query() {

		return "car/trip/query_trip";
	}

	@RequestMapping(value = "/trip/going", method = RequestMethod.GET)
	public String going() {

		return "car/trip/going_trip";
	}

	@RequestMapping(value = "/trip/print", method = RequestMethod.GET)
	public String print() {

		return "car/trip/print_trip";
	}

	/**
	 * 打印用户跑过的单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee/ptrip", method = RequestMethod.GET)
	public String printEmpTripRoute() {

		return "car/employee/print_trip";
	}

}
