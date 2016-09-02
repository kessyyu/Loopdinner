package com.project.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.domain.TestMenu;

@Controller
public class JsonMenuController {

	@RequestMapping(value="/jsonaddmenu",method=RequestMethod.POST )
	public @ResponseBody String getShopInJSON( ServletRequest req, ServletResponse resp, Model model) throws JsonProcessingException {
		HttpServletRequest request = (HttpServletRequest)req;
		//name = request.getParameter("name");
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		 
	        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
	        response.setHeader("Access-Control-Max-Age","3600");
	        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
	        response.setHeader("","");response.setHeader("","");
	        
		TestMenu m = new TestMenu();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
//		m.setName(name);
//		m.setPrice(pwd);
		ObjectMapper mapper = new ObjectMapper();  
		System.out.println(name);
		System.out.println(pwd);
		model.addAttribute("name",name);
		model.addAttribute("price",pwd);
		String Json =  mapper.writeValueAsString(model); 
		return Json;

	}
}
