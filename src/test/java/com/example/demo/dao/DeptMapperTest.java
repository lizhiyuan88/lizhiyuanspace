package com.example.demo.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Dept;
import com.example.demo.pojo.ShoppingCar;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;
import com.example.demo.util.MD5;

@SpringBootTest
public class DeptMapperTest {
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ShoppingCarMapper shoppingCarMapper;
	@Test
	public void testQueryAll() {
		Dept dept = deptMapper.selectByPrimaryKey(1);
		System.out.println(dept);
	}
	
	@Test
	public void testQueryMaxGoodsNo()throws Exception {
		String queryMaxGoods = goodsMapper.queryMaxGoods("G"+"%");
		
		System.out.println(queryMaxGoods);
	}
	
	
	@Test
	public void testlogin() {
		 UserInfo user = userInfoMapper.selsctByNameAndPwd("zhangsan", MD5.enctypeMD5(("haha")+123456));
		System.out.println(user);
	}
	
	@Test
	public void testAllstu() {
		 List<Student> list = studentMapper.listAllStu();
		 for(Object s:list) {
			 System.out.println(s);
		 }
		
	}
	
	@Test
	public void testadd() {
		ShoppingCar shoppingCar = new ShoppingCar();
		shoppingCar.setStuId(1);
		shoppingCar.setGoodsDetails("ss");
		shoppingCar.setGoodsNum(2);
		shoppingCar.setGoodsName("华为手机");
		int i = shoppingCarMapper.insert(shoppingCar);
		
			 System.out.println(i);
		 
		
	}
}
