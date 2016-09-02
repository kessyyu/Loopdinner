package com.project.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.domain.Admin;
import com.project.model.AdminModel;
import com.project.model.impl.AdminModelImpl;

@Controller
public class AdminController {
	@Resource(name = "adminService")
	private AdminModel adminService;

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public String loginRole(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter("loginrole");
		if (role.equals("Admin")) {
			Admin admin = new Admin();
			admin.setName(request.getParameter("name"));
			admin.setPassword(request.getParameter("pwd"));
			Admin a1 = this.adminService.login(admin);
			if (a1 != null) {
				request.getSession().setAttribute("adminuser", a1);
				return "index";
			} else {
				request.getSession().setAttribute("msg", "wrong");
				return "login";
			}
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	public String logoutRole(HttpServletRequest request, HttpServletResponse response) {
		// request.getSession().removeAttribute("adminuser");
		// cpage_orderrest
		// pagesize_rest_order
		// orderrestid

		Enumeration em = request.getSession().getAttributeNames();

		while (em.hasMoreElements()) {
			System.out.println(em.nextElement().toString());
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		return "login";
	}
	//
	//
	//
	//
	// @RequestMapping(value="/userlogin",method=RequestMethod.POST)
	// public String loginUser(HttpServletRequest request,HttpServletResponse
	// response){
	// String role = request.getParameter("loginrole");
	// if(role.equals("User")){
	// User user = new User();
	// user.setName(request.getParameter("name"));
	// //System.out.println("Ma"+request.getParameter("name"));
	// user.setPassword(request.getParameter("password"));
	// User a1 = this.userService.login(user);
	// if(a1!=null){
	// request.getSession().setAttribute("user", a1);
	// return "index0";
	// }else
	// return "index";
	//
	// }else {
	// Doctor d = new Doctor();
	// d.setName(request.getParameter("name"));
	// d.setPassword(request.getParameter("password"));
	// Doctor d1 = doctorService.login(d);
	// if(d1 != null){
	// request.getSession().setAttribute("doctor", d1);
	// return "index0";
	// }else
	// return "index";
	//
	// }
	// }
}
