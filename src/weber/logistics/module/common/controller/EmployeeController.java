package weber.logistics.module.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.framework.exception.BusinessException;
import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.mvc.model.JsonResponse;
import weber.logistics.framework.page.domain.JsonPage;
import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.until.ParameterCheck;
import weber.logistics.module.car.model.Trailer;
import weber.logistics.module.car.service.TrailerService;
import weber.logistics.module.common.model.Employee;
import weber.logistics.module.common.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class EmployeeController {

	private static Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TrailerService trailerService;

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("employee") @Valid Employee employee,
			BindingResult result) {

		logger.debug("  employee:" + employee);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}
		if (!ParameterCheck.checkProvinceId(employee.getProvince().getId())
				|| !ParameterCheck.checkNationId(employee.getNation().getId())) {
			logger.info("create 省份或民族参数错误: " + employee.getProvince().getId()
					+ ";" + employee.getNation().getId());
			throw new ParameterException("省份或民族参数错误");
		}

		employee.setEntryDate(new Date());
		employee.setLeaveDate(new Date());
		employee.setPassword("000000");
		employee.setSalt("none");

		employeeService.save(employee);

		return JsonResponse.getJsonMessage(1000, "添加成功");
	}

	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public @ResponseBody Employee get(
			@PathVariable(value = "employeeId") String employeeId) {

		if (!ParameterCheck.checkEmpId(employeeId)) {
			logger.warn("get-非法请求信息employeeId:" + employeeId);
			throw new ParameterException("非法请求信息!");
		}

		Employee result = employeeService.get(new Employee(employeeId));

		if (result == null) {
			throw new BusinessException("查询员工不存在！");
		}

		return result;
	}

	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("employee") @Valid Employee employee,
			@PathVariable(value = "employeeId") String employeeId,
			BindingResult result) {

		logger.debug("  employee:" + employee);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		if (!ParameterCheck.checkEmpId(employeeId)) {
			throw new ParameterException("非法请求信息!");
		}

		employee.setId(employeeId);

		employeeService.update(employee);

		logger.debug("update 修改成功 " + employee);
		return JsonResponse.getJsonMessage(1000, "修改成功");
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionsPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? page : 10;
		name = (name != null && !name.equals("")) ? name : null;

		PageBounds pageBound = new PageBounds(page, limit);// 默认分页，返回PageList
		Map<String, Object> map = new HashMap<String, Object>();
		Employee employee = new Employee();
		employee.setName(name);
		map.put("employees",
				new JsonPage(employeeService.findByConditionPage(employee,
						pageBound)));
		return map;
	}

	//
	@RequestMapping(value = "/employee/{employeeId}/trailer", method = RequestMethod.GET)
	public @ResponseBody Trailer getEmpTrailer(
			@PathVariable(value = "employeeId") String employeeId) {

		logger.debug("  employeeId:" + employeeId);

		if (employeeId == null) {
			logger.warn("getEmpTrailer 非法请求信息! employeeId " + employeeId);
			throw new ParameterException("非法请求信息!");
		}
		if (employeeService.get(new Employee(employeeId)) == null) {
			throw new BusinessException("司机不存在！");
		}
		Trailer result = trailerService.findByEmp(employeeId);

		return result;
	}

}
