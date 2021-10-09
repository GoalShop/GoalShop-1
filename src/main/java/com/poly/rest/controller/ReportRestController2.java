package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.ReportDao;
import com.poly.entity.Product;
import com.poly.entity.Report;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/report1")
public class ReportRestController2 {
	@Autowired
	ReportDao reportDao;
	
	@GetMapping()
	public List<Report>  getAll() {
		return reportDao.revenueByCustomer();
	}
}
