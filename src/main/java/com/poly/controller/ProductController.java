package com.poly.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.ProductDao;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductDao pdao;
	
	
	@RequestMapping({"/"})
	public String home() { 
		return "redirect:/product/list";
	}
	
	@GetMapping("/product/list")
	public String index(Model model,HttpServletRequest request
			,RedirectAttributes redirect) {
		request.getSession().setAttribute("productlist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/product/list/page/1";
	}
	
	@GetMapping("/product/list/page/{pageNumber}")
	public String showProductPage(HttpServletRequest request,
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		int pagesize = 9;
		List<Product> list =(List<Product>) productService.findAll();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("productlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/product/list/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("items", pages);
		return "product/list";
	}
	
//	@RequestMapping("/product/list")
//	public String list(Model model, @RequestParam("cid") Optional<Integer> cid ,Optional<Integer> tid 
//			, @RequestParam("page") Optional<Integer> page) {
//		int x = page.orElse(0);
//		if (x < 0) {
//		}
//		model.addAttribute("page", x);
//		int size = 9;
//		if (cid.isPresent() && (tid.isPresent())) {
//			List<Product> list = productService.findByCategoryId(cid.get());
//			model.addAttribute("items", list);
//			List<Product> list1 = productService.findByTrademarkId(tid.get());
//			model.addAttribute("items", list1);
//		}
//		
//		else {
//
//			Page<Product> list = pdao.findAll(PageRequest.of(x, size));
//			model.addAttribute("items", list);
//		}
//
//		return "product/list";
//	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id")Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item",item);
		return "product/detail";
	}
	@RequestMapping("/product/discount")
	public String discount(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 9;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findByDis(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/discount";
	}
	@RequestMapping("/product/latest")
	public String latest(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 9;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findByLatest(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/latest";
	}
	@RequestMapping("/product/special")
	public String special(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 9;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findBySpecial(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/special";
	}
	
	@GetMapping("/product/list/search")
	public String search(@RequestParam("keywords") String keywords, Model model) {
		if (keywords.equals("")) {
			return "redirect:/product/list";
		}
		model.addAttribute("items", productService.findByKeywords(keywords));
		return "list";
	}
	
	@GetMapping("/product/list/search/{pageNumber}")
	public String search(@RequestParam("keywords") String keywords, Model model, HttpServletRequest request,
			@PathVariable int pageNumber) {
		if (keywords.equals("")) {
			return "redirect:/product/list";
		}
		List<Product> list = productService.findByKeywords(keywords);
		if (list == null) {
			return "redirect:/product/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		int pagesize = 9;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("productlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/product/list/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("items", pages);
		return "product/list";
	}
	
	
	
}
