package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Goods;
import com.example.demo.pojo.ShoppingCar;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;

@SpringBootTest
public class UserMapperTest {
	@Autowired
	private ICardService cardService;
	
	@Test
	public void testLogin() {
		UserInfo user = cardService.selsctByNameAndPwd("zhangsan", "123456");
		System.out.println(user);
	}
	
	@Test
	public void testAllStu() {
		List<Student> listAllStu = cardService.listAllStu();
		for(Object s:listAllStu) {
			System.out.println(s);
		}
		
	}
	
	@Test
	public void testAddstu() {
		Student stu=new Student();
		stu.setStuLogin("wangwu");
		stu.setStuName("王五");
		stu.setStuPasswd("123456");
		stu.setCollegeId(1);
		stu.setStuRemain(100);
		Boolean rt = cardService.addStu(stu);
		System.out.println(rt);
	}	
	@Test
	public void testQueryStu() {
		
		List<Student>  list=cardService.queryStuByName("张三");
		System.out.println(list);
		
	}
	
	@Test
	public void testListGoods() {
		
		List<Goods> list = cardService.listAllGoods();
		System.out.println(list);
		
	}
	@Test
	public void testaddsp() {
		ShoppingCar shoppingCar = new ShoppingCar();
		shoppingCar.setStuId(1);
		shoppingCar.setGoodsDetails("ss");
		shoppingCar.setGoodsNum(2);
		shoppingCar.setGoodsName("华为手机");
		
		boolean rt = cardService.addShoppingCar(shoppingCar);
		System.out.println(rt);
		
	}
	
}
