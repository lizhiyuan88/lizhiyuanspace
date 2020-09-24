package com.example.demo.service;


import java.util.List;

import com.example.demo.pojo.College;
import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.ShoppingCar;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;

public interface ICardService {
	
	boolean deleteshopping(Integer carId);
	
	List<ShoppingCar> listAllGoods (Integer stuId);
	
	boolean addShoppingCar(ShoppingCar shoppingCar);
	
	Goods selectGoodsById(Integer goodsId);
	
	Student selectByNameAndPwd(String uname,String upass);
	
	boolean updateUserInfoPwd(UserInfo userInfo);
	
	List<Goods> queryGoodsByName(String goodsName);
	
	boolean deleteGoods(Integer goodsId);
	
	boolean addGoods(Goods goods);
	
	Goods selectGoods(Integer goodsId);
	
	boolean updateGoods(Goods goods);
	
	List<Goods> listAllGoods();
	
	List<Student> queryStuByName(String stuName);
	
	boolean deleteByPrimaryKey(Integer stuId);
	
	Boolean addStu(Student stu);
	
	boolean updateStu(Student record);
	
	List<College> ListAllColege();
	
	Student selectByPrimaryKeyStu(Integer stuId);
	
	List<Student> listAllStu();
	
	Dept selectByPrimaryKey(Integer deptId);
	 
	UserInfo selsctByNameAndPwd(String uname,String upass );
	
	
}
