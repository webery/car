package weber.logistics.module.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")
public class TripRouteViewController {

	/**
	 * 打印客户的路线
	 * 
	 * @return
	 */
	@RequestMapping(value = "/client/ptripRoute", method = RequestMethod.GET)
	public String printEmpTripRoute() {

		return "car/client/print_tripRoute";
	}

}
