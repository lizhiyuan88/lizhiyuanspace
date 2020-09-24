package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.ShoppingCar;

public interface ShoppingCarMapper {
	
	List<ShoppingCar> listAllGoods (Integer stuId);
	
    int deleteByPrimaryKey(Integer carId);

    int insert(ShoppingCar record);

    int insertSelective(ShoppingCar record);

    ShoppingCar selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(ShoppingCar record);

    int updateByPrimaryKey(ShoppingCar record);
}