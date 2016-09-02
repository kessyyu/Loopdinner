package com.project.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.User;
import com.project.model.UserModel;
import com.project.util.HibernatePage;
import com.project.util.PagesDomain;

@Controller
public class UserController {
	@Resource(name="userService")
	private UserModel userService;
	
	private HibernatePage hp = new HibernatePage();
	
	
	
	public HibernatePage getHp() {
		return hp;
	}

	public void setHp(HibernatePage hp) {
		this.hp = hp;
	}

	@RequestMapping(value="userslist", method=RequestMethod.GET)
	public String userList(HttpServletRequest request){
		//List<User>users = userService.queryAll("from com.project.domain.User");
		int pagesize = 2;
		List<User>users = userService.h_Page("from com.project.domain.User", 0, pagesize);
		request.getSession().setAttribute("users", users);
		int cpage = 1;
		request.getSession().setAttribute("cpage", cpage);
		request.getSession().setAttribute("pagesize", pagesize);
		return "userlist";
	}
	
	@RequestMapping(value="pageup", method=RequestMethod.GET)
	public String upPage(@RequestParam(value="cpage",required=false)  int cpage, HttpServletRequest request){
		PagesDomain pd = new PagesDomain();
		int pagesize = (Integer) request.getSession().getAttribute("pagesize");
		List<User>totalusers = userService.queryAll("from com.project.domain.User");
		int count = totalusers.size();
		pd = hp.turnPage(cpage, pagesize, count);
		
		request.getSession().setAttribute("cpage", pd.getCpage());
	
		List<User>users = userService.h_Page("from com.project.domain.User", pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("users", users);
		return "userlist";
	}
	
	@RequestMapping(value="/viewuser", method=RequestMethod.GET)
	public String viewUserDetail(@RequestParam(value="uid",required=false)  int id, HttpServletRequest request){
		User user = userService.queryOne("User", id);
		request.getSession().setAttribute("userdetails", user);
		return "userdetail";
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.GET)
	public String updateUserDetail(@RequestParam(value="uid",required=false)  int id, HttpServletRequest request){
		User user = userService.queryOne("User", id);
		request.getSession().setAttribute("userd", user);
		return "userupdate";
	}
	
	@RequestMapping(value="/confirmupdateuser",method=RequestMethod.POST)
	public String confirmUserUpdate(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("userd");
		User user_update = new User();
		
		user_update.setId(user.getId());
		System.out.println(user.getId());
		user_update.setName(request.getParameter("name"));
		user_update.setEmail(request.getParameter("email"));
		user_update.setPhone(request.getParameter("phone"));
		user_update.setAge(Integer.parseInt(request.getParameter("age")));
		user_update.setPassword(request.getParameter("password"));
		user_update.setSex(request.getParameter("sex"));
		user_update.setFood_habits(request.getParameter("description"));
		
		userService.update(user_update);
		return "redirect:/userslist";
	}
	
	@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
	public String deleteUserDetail(@RequestParam(value="uid",required=false)  int id, HttpServletRequest request){
		User user = userService.queryOne("User", id);
		if(user != null){
			userService.del(user);
		}
		return "redirect:/userslist";
	}
	
	@RequestMapping(value="/searchuser",method=RequestMethod.POST)
	public String searchByUser(HttpServletRequest request){
		int pagesize = 2;
		String searchtext = request.getParameter("unamesearch");
		List<User> useresult = userService.search("User", searchtext,0,pagesize);
		request.getSession().setAttribute("usernamesearch", useresult);
		int cpage_usear = 1;
		request.getSession().setAttribute("cpage_usear", cpage_usear);
		request.getSession().setAttribute("pagesize", pagesize);
		request.getSession().setAttribute("searchtext", searchtext);
		return "userlistsearch";
		
	}
	
	@RequestMapping(value="pageusersearch", method=RequestMethod.GET)
	public String userSearchTurnPage(@RequestParam(value="cpage_usear",required=false)  int cpage, HttpServletRequest request){
		//System.out.println("CU:"+cpage);
		PagesDomain pd = new PagesDomain();
		String searchtext = (String) request.getSession().getAttribute("searchtext");
		int pagesize = (Integer) request.getSession().getAttribute("pagesize");
		List<User>totalusersearch = userService.searchTotals("User", searchtext);
		int count = totalusersearch.size();
		//System.out.println("Count"+count);
		pd = hp.turnPage(cpage, pagesize, count);
		//System.out.println("Cpage:"+pd.getCpage()+"From_p:"+pd.getFrom_page()+"Count"+count);
		request.getSession().setAttribute("cpage_usear", pd.getCpage());
		List<User>usernamesearch = userService.search("User", searchtext, pd.getFrom_page(),  pagesize);
		//List<User>usernamesearch = userService.search("from com.project.domain.User", pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("usernamesearch", usernamesearch);
		return "userlistsearch";
	}
	
}
