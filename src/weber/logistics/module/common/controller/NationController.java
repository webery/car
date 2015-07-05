package weber.logistics.module.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weber.logistics.module.car.doc.NationExcelView;
import weber.logistics.module.common.model.Nation;
import weber.logistics.module.common.service.NationService;

@Controller
@RequestMapping("/api")
public class NationController {

	@Autowired
	private NationService nationService;

	@RequestMapping(value = "/nations", method = RequestMethod.GET)
	public @ResponseBody List<Nation> findAll() {

		return nationService.findAll();
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ModelAndView query(ModelMap model, HttpServletRequest request) {

		List<Nation> list = nationService.findAll();
		NationExcelView viewExcel = new NationExcelView();
		// 将查询出的list集合存入ModelMap 对象中，此时的key就是ViewExcel类中Map所对应的key
		model.put("list", list);
		return new ModelAndView(viewExcel, model);
	}

}
