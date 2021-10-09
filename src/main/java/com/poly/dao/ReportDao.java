package com.poly.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Report;

public interface ReportDao extends JpaRepository<Report, Serializable>{
	@Query("SELECT new Report(o.category.name, "
			+ " sum(o.Unit_price * o.Quantity), "
			+ " sum(o.Quantity), "
			+ " min(o.Unit_price), "
			+ " max(o.Unit_price), "
			+ " avg(o.Unit_price)) "
			+ " FROM Product o "
			+ " GROUP BY o.category.name")
	List<Report> inventoryByCategory();

	@Query("SELECT new Report(o.product.category.name, "
			+ " sum(o.Price * o.Quantity), "
			+ " sum(o.Quantity), "
			+ " min(o.Price), "
			+ " max(o.Price), "
			+ " avg(o.Price)) "
			+ " FROM OrderDetail o "
			+ " GROUP BY o.product.category.name")
	List<Report> revenueByCategory();
	
	@Query("SELECT new Report(o.order.account.Username, "
			+ " sum(o.Price * o.Quantity), "
			+ " sum(o.Quantity), "
			+ " min(o.Price), "
			+ " max(o.Price), "
			+ " avg(o.Price)) "
			+ " FROM OrderDetail o "
			+ " GROUP BY o.order.account.Username"
			+ " ORDER BY sum(o.Price * o.Quantity) DESC")
	List<Report> revenueByCustomer();
	
	@Query("SELECT new Report(month(o.order.CreateDate), "
			+ " sum(o.Price * o.Quantity), "
			+ " sum(o.Quantity), "
			+ " min(o.Price), "
			+ " max(o.Price), "
			+ " avg(o.Price)) "
			+ " FROM OrderDetail o "
			+ " GROUP BY month(o.order.CreateDate)"
			+ " ORDER BY month(o.order.CreateDate)")
	List<Report> revenueByMonth();
}