package com.poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.dao.ProductDao;
import com.poly.entity.Account;
import com.poly.entity.Product;
import com.poly.service.AccountService;

@Controller
public class HomeController {
	@Autowired
	ProductDao pdao;
	@Autowired
	AccountService accservice;
	@Autowired
	AccountDao dao;
	@RequestMapping({"/home/index","/"})
	public String home(Model model) {
		List<Product> list = pdao.findByAllDis();
		model.addAttribute("item1", list);
		List<Product> list1 = pdao.findByAllSpe();
		model.addAttribute("item2", list1);
		List<Product> list2 = pdao.findByAllLat();
		model.addAttribute("item3", list2);
		return "home/index";
	}
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin(Model model,@RequestParam("username") String username) {
		
		Account acc = accservice.findById(username);
		model.addAttribute("acc", acc);
		return "redirect:/assets/admin/index.html";
	}
	@RequestMapping({"/admin1","/admin1/home/index"})
	public String admin1() {
		return "redirect:/assets/admin1/index.html";
	}
	
	
}
