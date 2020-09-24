package com.example.demo.web;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.College;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;
import com.example.demo.service.ICardService;
import com.example.demo.util.MD5;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")//设置返回自动组装成json格式
public class UserController {
	@Autowired
	private ICardService cardService;
	
	@RequestMapping(path="/login",method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Boolean verifyLogin(@RequestBody UserInfo userinfo,HttpSession session){
		Boolean rt=false;
		UserInfo user = cardService.selsctByNameAndPwd(userinfo.getUserLogin(), userinfo.getUserPasswd());
		if(user!=null) {
			session.setAttribute("user", user);
			rt=true;
		}
		return rt;
	}
	
	@RequestMapping(path="/username",method = {RequestMethod.GET,RequestMethod.POST})
	public  String getUserName(HttpSession session){
		UserInfo user = (UserInfo)session.getAttribute("user");
		if(user!=null) {
			return user.getUserName();
		}
		return null;
	}
	@RequestMapping(path="/liststu",method = {RequestMethod.GET,RequestMethod.POST})
	public  List<Student> showAllstu(HttpSession session) {
		List<Student> stuList= cardService.listAllStu();
		
		return stuList;
	}
	
	@RequestMapping(path="/querystu",method = {RequestMethod.GET,RequestMethod.POST})
	public Student queryStu(Integer stuId,HttpSession session) {
		
		
		return cardService.selectByPrimaryKeyStu(stuId);
	}
	
	@RequestMapping(path="/savestu",method = {RequestMethod.GET,RequestMethod.POST})
	public Boolean saveStudent(@RequestBody Student student) {
		
		
		return cardService.updateStu(student);
	}
	
	@RequestMapping(path="/addstu",method = {RequestMethod.GET,RequestMethod.POST})
	public Boolean addStudent(@RequestBody Student student) {
			System.out.println(student);
		
		return cardService.addStu(student);
	}
	
	@RequestMapping(path="/delAllStu",method = {RequestMethod.GET,RequestMethod.POST})
	public Boolean delStudent( Integer[] id) {
		boolean rt=false;
		for(Integer stuId:id) {
			rt = cardService.deleteByPrimaryKey(stuId);
			if(!rt) {
				rt=false;
			}
		}
		return rt;
	}
	
	@RequestMapping(path="/queryStu",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Student> queryStu(String stuName ) {
		return cardService.queryStuByName(stuName);
	}
	
	@RequestMapping(path="/updatePwd",method = {RequestMethod.GET,RequestMethod.POST})
	public Boolean updataPwd(String oldPasswd,String newPasswd,HttpSession session ) {
		boolean rt=false;
		UserInfo  userInfo =(UserInfo) session.getAttribute("user");	
		String oldpwd = userInfo.getUserPasswd();
		
		if(oldpwd.equals(MD5.enctypeMD5(("haha")+oldPasswd))) {
			userInfo.setUserPasswd(MD5.enctypeMD5(("haha"+newPasswd)));
			rt=cardService.updateUserInfoPwd(userInfo);
		}
		return rt;
	}
	
	
//	@RequestMapping("/")
//	public ModelAndView indexPage() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("index");
//		return mv;
//	}
//	@RequestMapping("/test")
//	public ModelAndView test() {
//		ModelAndView mv = new ModelAndView();
//		Dept dept = cardService.selectByPrimaryKey(1);
//		mv.addObject("dept",dept);
//		mv.setViewName("result");
//		return mv;
//	}
	
	
}
