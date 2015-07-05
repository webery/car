package weber.logistics.module.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.module.common.model.Province;
import weber.logistics.module.common.service.ProvinceService;

@Controller
@RequestMapping("/api")
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public @ResponseBody List<Province> queryAll() {

		return provinceService.findAll();
	}
}
