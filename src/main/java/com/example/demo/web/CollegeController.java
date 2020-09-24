package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.College;
import com.example.demo.service.ICardService;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class CollegeController {
	@Autowired
	private ICardService cardService;
	
	@RequestMapping(path="/listcollege",method = {RequestMethod.GET,RequestMethod.POST})
	public List<College> showAllCollege(HttpSession session){
		return cardService.ListAllColege();
	}

}
