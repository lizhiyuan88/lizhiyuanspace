package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Goods;

public interface GoodsMapper {
	
	List<Goods> queryGoodsByName(String goodsName);
	
	String queryMaxGoods(String prefix);
	
	List<Goods> listAllGoods();
	
	
	
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}