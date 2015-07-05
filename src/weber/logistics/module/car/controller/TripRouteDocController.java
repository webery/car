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
import weber.logistics.module.car.doc.ClientTripRouteExcelView;
import weber.logistics.module.car.doc.EmpTripExcelView;
import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.service.TripRouteService;
import weber.logistics.module.car.service.TripService;
import weber.logistics.module.common.model.Employee;

@Controller
@RequestMapping("/doc/tripRoute")
public class TripRouteDocController {

	@Autowired
	private TripRouteService tripRouteService;

	@RequestMapping(value = "/allClientTripRoute/excel", method = RequestMethod.GET)
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

		List<Client> clients = tripRouteService
				.findAllClientTripByYearMonth(yearMonth);
		ClientTripRouteExcelView excelView = new ClientTripRouteExcelView();
		model.put("clients", clients);
		model.put("yearMonth", yearMonth);

		return new ModelAndView(excelView, model);
	}

}
