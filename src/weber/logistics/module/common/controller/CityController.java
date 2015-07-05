package weber.logistics.module.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.module.common.model.City;
import weber.logistics.module.common.service.CityService;

@Controller
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/citys", method = RequestMethod.GET)
	public @ResponseBody List<City> queryByProvince(Integer province) {
		return cityService.findByProvinceId(province);
	}

}
