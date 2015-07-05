package weber.logistics.module.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import weber.logistics.framework.exception.ParameterException;
import weber.logistics.framework.mvc.model.JsonMessage;
import weber.logistics.framework.mvc.model.JsonResponse;
import weber.logistics.framework.until.MdSaltUtil;
import weber.logistics.module.common.model.Admin;
import weber.logistics.module.common.service.AdminService;

@Controller
@RequestMapping("/api")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JsonMessage login(
			@ModelAttribute("admin") @Valid Admin admin, BindingResult result,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			logger.warn("logout 输入账号或者密码格式有错!" + result);
			throw new ParameterException("输入账号或者密码格式有错!");
		}
		Admin user = adminService.findByAccount(admin);

		if (user == null) {
			return JsonResponse.getJsonMessage(1000, "账号不存在!");
		} else if (!MdSaltUtil.verify(admin.getPassword(), user.getPassword())) {
			return JsonResponse.getJsonMessage(1000, "密码错误!");
		}
		request.getSession().setAttribute("user", user);
		return JsonResponse.getJsonMessage(1000, "登陆成功");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody JsonMessage logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return JsonResponse.getJsonMessage(1000, "退出成功");
	}
}
