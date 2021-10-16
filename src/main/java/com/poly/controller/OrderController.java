package com.poly.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.OrderDao;
import com.poly.dao.OrderDetailDao;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	OrderDao odao;
	@Autowired
	OrderDetailDao orderDetailDao;
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	@RequestMapping("/order/list")
	public String list(Model model , HttpServletRequest request, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {}
		model.addAttribute("page", x);
		int size = 9;
		String username= request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id , Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
	
	@RequestMapping("/order/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		orderDetailDao.deleteOrderId(id);
		orderService.deleteById(id);
		return "redirect:/order/list";
	}
	
}