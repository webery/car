package weber.logistics.module.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.module.common.service.NationService;
import weber.logistics.module.common.service.ProvinceService;

@Controller
@RequestMapping("/api")
public class NtPvController {

	@Autowired
	private NationService nationService;
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/ntpvs", method = RequestMethod.GET)
	public @ResponseBody Map<String, List> queryAll() {
		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("nations",
		 * nationService.findAll()); jsonObject.put("provinces",
		 * provinceService.findAll());
		 * //commons.lang.exception.NestableRuntimeException
		 */
		Map<String, List> jsonObject = new HashMap<String, List>();
		jsonObject.put("nations", nationService.findAll());
		jsonObject.put("provinces", provinceService.findAll());
		return jsonObject;
	}
}
