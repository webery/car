package weber.logistics.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view/employee")
public class EmployeeViewController {

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "car/employee/create_employee";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {

		return "car/employee/query_employee";
	}

}
