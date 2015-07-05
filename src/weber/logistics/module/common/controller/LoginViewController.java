package weber.logistics.module.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weber.logistics.framework.until.MdSaltUtil;
import weber.logistics.module.common.model.Admin;
import weber.logistics.module.common.service.AdminService;

@Controller
@RequestMapping("/view")
public class LoginViewController {

	private static Logger logger = Logger.getLogger(LoginViewController.class);
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "home/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("admin") @Valid Admin admin,
			BindingResult result, Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			logger.debug("输入账号或者密码格式有错!" + result);
			model.addAttribute("message", "输入账号或者密码格式有错!");
			return "home/login";
		}
		Admin user = adminService.findByAccount(admin);

		if (user == null) {
			model.addAttribute("message", "账号或密码不存在");
			return "home/login";
		}
		if (!MdSaltUtil.verify(admin.getPassword(), user.getPassword())) {
			model.addAttribute("message", "账号或密码不存在");
			return "home/login";
		}

		request.getSession().setAttribute("user", user);
		return "redirect:/view/";
	}
}
