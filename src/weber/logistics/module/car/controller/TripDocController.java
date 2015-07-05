package weber.logistics.module.car.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import weber.logistics.framework.exception.ParameterException;
import weber.logistics.module.car.doc.EmpTripExcelView;
import weber.logistics.module.car.service.TripService;
import weber.logistics.module.common.model.Employee;

/**
 * 派单doc导出视图控制器
 * 
 * @author weber
 *
 */

@Controller
@RequestMapping("/doc/trip")
public class TripDocController {

	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/allempTrip/excel", method = RequestMethod.GET)
	public ModelAndView print(
			@RequestParam(value = "yearMonth", required = true) String yearMonth,
			ModelMap model) {

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date printDate;
		try {
			printDate = sdf.parse(yearMonth);
			if (now.before(printDate)) {
				throw new ParameterException("打印时间不能早于当前时间!");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ParameterException("请输入yyyy-mm的日期格式!");
		}

		List<Employee> employees = tripService
				.findAllEmpTripByYearMonth(yearMonth);
		EmpTripExcelView tripExcelView = new EmpTripExcelView();
		model.put("employees", employees);
		model.put("yearMonth", yearMonth);

		return new ModelAndView(tripExcelView, model);
	}

}
