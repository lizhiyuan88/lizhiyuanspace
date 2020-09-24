package com.example.demo.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.DeptMapper;
import com.example.demo.dao.GoodsMapper;
import com.example.demo.dao.ShoppingCarMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.dao.UserInfoMapper;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.ShoppingCar;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;
import com.example.demo.util.MD5;
@Service
public class CarServiceImpl implements ICardService {
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ShoppingCarMapper shoppingCarMapper;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Dept selectByPrimaryKey(Integer deptId) {
		return deptMapper.selectByPrimaryKey(deptId);
	}
	@Override
	//后台登录
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public UserInfo selsctByNameAndPwd(String uname, String upass) {
		
		return userInfoMapper.selsctByNameAndPwd(uname, MD5.enctypeMD5(("haha"+upass)));
	}
	
	@Override
	//学生登录
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Student selectByNameAndPwd(String uname, String upass) {
		// TODO Auto-generated method stub
		return studentMapper.selectByNameAndPwd(uname, MD5.enctypeMD5(("haha"+upass)));
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<Student> listAllStu() {
		
		return studentMapper.listAllStu();
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Student selectByPrimaryKeyStu(Integer stuId) {
		
		return studentMapper.selectByPrimaryKey(stuId);
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<College> ListAllColege() {
		
		return collegeMapper.ListAllColege();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateStu(Student record) {
		
		return studentMapper.updateByPrimaryKeySelective(record)>0;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public Boolean addStu(Student stu) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = ("0" + month).substring(month >= 10 ? 1 : 0);
		String prefix = "STU" + year + monthStr;
		String maxStuNo = studentMapper.queryMaxStuNoByMonth(prefix + "%");
		if (maxStuNo == null) {
			stu.setStuNo(prefix + "001");
		} else {
			String newNo = Integer.parseInt(maxStuNo.substring(maxStuNo.length() - 3)) + 1 + "";
			String stuNo = prefix + ("00" + newNo).substring(newNo.length() - 1);
			stu.setStuNo(stuNo);
		}
		// 处理姓名
		String maxName = studentMapper.getSameNameMaxName("^" + stu.getStuName() + "[1-9]{0,2}$");
		if (maxName != null) {
			String newName = stu.getStuName() + (Integer.parseInt(maxName.replace(stu.getStuName(), "0")) + 1);
			stu.setStuName(newName);
		}
		stu.setStuPasswd(MD5.enctypeMD5(("haha")+stu.getStuPasswd()));
		
		return studentMapper.insertSelective(stu)>0;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean addGoods(Goods goods) {
		String prefix="G";
		String maxGoodsNo = goodsMapper.queryMaxGoods(prefix+"%");
//		System.out.println(maxGoodsNo);
		if(maxGoodsNo==null) {
			goods.setGoodsNo(prefix+"001");
		}else {
			String newNo=Integer.parseInt(maxGoodsNo.substring(maxGoodsNo.length()-3))+1+"";
			String goodsNo=prefix+("00"+newNo).substring(newNo.length()-1);
			goods.setGoodsNo(goodsNo);
		}
	
		return goodsMapper.insertSelective(goods)>0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean deleteByPrimaryKey(Integer stuId) {
		return studentMapper.deleteByPrimaryKey(stuId)>0;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<Student> queryStuByName(String stuName) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuByName("%"+stuName+"%");
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<Goods> listAllGoods() {
		
		return goodsMapper.listAllGoods();
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		return goodsMapper.updateByPrimaryKeySelective(goods)>0;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Goods selectGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(goodsId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean deleteGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.deleteByPrimaryKey(goodsId)>0;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<Goods> queryGoodsByName(String goodsName) {
		// TODO Auto-generated method stub
		return goodsMapper.queryGoodsByName("%"+goodsName+"%");
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateUserInfoPwd(UserInfo userInfo) {
//		UserInfo userInfo2 = new UserInfo();
//		String newPwd = userInfo.getUserPasswd();
//		userInfo2.setUserPasswd(MD5.enctypeMD5(("haha"+newPwd)));
		return userInfoMapper.updateByPrimaryKeySelective(userInfo)>0;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Goods selectGoodsById(Integer goodsId) {
		
		return goodsMapper.selectByPrimaryKey(goodsId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean addShoppingCar(ShoppingCar shoppingCar) {
		
		return shoppingCarMapper.insertSelective(shoppingCar)>0;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<ShoppingCar> listAllGoods(Integer stuId) {
		
		return shoppingCarMapper.listAllGoods(stuId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean deleteshopping(Integer carId) {
		
		return shoppingCarMapper.deleteByPrimaryKey(carId)>0;
	}
	
	
	

}
