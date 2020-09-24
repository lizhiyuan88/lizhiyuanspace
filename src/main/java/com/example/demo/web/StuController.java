package com.example.demo.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;
import com.example.demo.service.ICardService;

@RestController
@RequestMapping("/stu")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")//设置返回自动组装成json格式
public class StuController {
	@Autowired
	private ICardService cardService;
	@RequestMapping(path="/login",method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Boolean verifyLogin(@RequestBody Student student,HttpSession session) {
		boolean rt=false;
		Student stu=cardService.selectByNameAndPwd(student.getStuLogin(), student.getStuPasswd());
		if(stu!=null) {
			session.setAttribute("stu", stu);
			rt=true;
		}
		return rt;
	}
	
	@RequestMapping(path="/stuname",method = {RequestMethod.GET,RequestMethod.POST})
	public  String getUserName(HttpSession session){
		Student stu = (Student)session.getAttribute("stu");
		if(stu!=null) {
			return stu.getStuName();
		}
		return null;
	}
}
