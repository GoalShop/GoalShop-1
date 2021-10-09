package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Favorite;
import com.poly.entity.Order;

public interface FavoriteDao extends JpaRepository<Favorite, Integer> {

}
