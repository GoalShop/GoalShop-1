package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm( Model model) {
		model.addAttribute("message","vui long dang nhap");
		return "security/login";
	}
	@RequestMapping("/security/login/success")
	public String loginSuccess( Model model) {
		model.addAttribute("message","dang nhap thanh cong");
		return "security/login";
	}
	@RequestMapping("/security/login/erorr")
	public String loginErorr( Model model) {
		model.addAttribute("message","dang nhap that bai");
		return "security/login";
	}
	@RequestMapping("/security/unauthoried")
	public String unauthoried( ) {
		return "redirect:/assets/admin/404.html";
	}
	@RequestMapping("/security/logoff/success")
	public String logoff( Model model) {
		model.addAttribute("message","dang xuat thanh cong ");
		return "security/login";
	}
}
