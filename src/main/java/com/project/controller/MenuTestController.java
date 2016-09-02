package com.project.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopdiner.restaurant.MenuTable;
import com.loopdiner.restaurant.RestaurantService;
import com.project.domain.Restaurant;
import com.project.domain.TestMenu;
import com.project.model.RestaurantModel;

import sun.misc.BASE64Decoder;

@Controller
//@RequestMapping("/kfc/brands")
public class MenuTestController {
	private TestMenu m;
	@Resource(name = "restService")
	private RestaurantService restService;
	public TestMenu getMenu() {
		return m;
	}

	public void setMenu(TestMenu m) {
		this.m = m;
	}

	//@RequestMapping(value="/addtest",method=RequestMethod.POST )
	@RequestMapping(value="/addtest",method=RequestMethod.POST )
	public @ResponseBody String ghopInJSON( @RequestBody TestMenu menu, ServletRequest req, ServletResponse resp, Model model) throws JsonProcessingException {
		HttpServletRequest request = (HttpServletRequest)req;
		//name = request.getParameter("name");
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		System.out.println("1324234");
		TestMenu m = new TestMenu();
//		String name = request.getParameter("name");
//		String pwd = request.getParameter("pwd");
//		m.setName(name);
//		m.setPrice(pwd);
		ObjectMapper mapper = new ObjectMapper();  
		System.out.println(menu.getName());
		System.out.println(menu.getPrice());
		model.addAttribute("name",menu.getName());
		model.addAttribute("price",menu.getPrice());
		String Json =  mapper.writeValueAsString(model); 
		return Json;
		
		

	}
	
	
	
	@RequestMapping(value = "saveUser", method = {RequestMethod.POST}) 
    @ResponseBody  
    public String saveUser(@RequestBody List<TestMenu> users, ServletRequest req, ServletResponse resp) { 
		HttpServletRequest request = (HttpServletRequest)req;
		//name = request.getParameter("name");
		HttpServletResponse response = (HttpServletResponse) resp;
//		response.setHeader("Access-Control-Allow-Origin","*");
//		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
//		response.setHeader("Access-Control-Allow-Methods", "POST");  
//        response.setHeader("Allow", "POST");  
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, DELETE, OPTIONS"); 
		response.setHeader("Access-Control-Allow-Headers","Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
		response.setHeader("Content-Type", "text/html; charset=utf-8");

         System.out.println(users.size());
         return String.valueOf(users.size());
    } 
	
	
	@RequestMapping(value = "query", method = {RequestMethod.GET}) 
    @ResponseBody  
    public String saveUser(@RequestParam(value="ids",required=false)  int id, HttpServletRequest request ) { 
		

         System.out.println(id);
         return String.valueOf(12);
    } 
	
	
	
	@RequestMapping(value = "saveR", method = {RequestMethod.POST}) 
    @ResponseBody  
    public String saveRests(@RequestBody List<Restaurant> rs, ServletRequest req, ServletResponse resp) { 
		HttpServletRequest request = (HttpServletRequest)req;
		//name = request.getParameter("name");
		HttpServletResponse response = (HttpServletResponse) resp;

//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, DELETE, OPTIONS"); 
//		response.setHeader("Access-Control-Allow-Headers","Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
//		response.setHeader("Content-Type", "text/html; charset=utf-8");
		
		String restname = rs.get(0).getName();
		String restloca = rs.get(0).getLocation();
		System.out.println("Restname:"+restname);
		System.out.println("Location:"+restloca);
		//System.out.println("menu size:"+rs.get(0).getMenusrest().size());

		//Set<MenuTable> mset = rs.get(0).getMenusrest();
		Iterator<MenuTable>it = rs.get(0).getMenus().iterator();
		List<MenuTable> mtlist = new ArrayList<MenuTable>();
		while(it.hasNext()){
			mtlist.add(it.next());
			
		}
		for(int i = 0; i < mtlist.size(); i++){
			String mname = mtlist.get(i).getName();
			String mdescp = mtlist.get(i).getMenu_desc();
			//String m_desc = (String)it.next().getMenu_desc();
			System.out.println("MenuName:"+mname+"  Menudescp:"+mdescp);
		}
         System.out.println(rs.size());
         return String.valueOf(rs.size());
    } 
	
	
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)  
	 public @ResponseBody String order(ServletRequest req, ServletResponse resp, Model model) {  
		HttpServletRequest request = (HttpServletRequest)req;
		//name = request.getParameter("name");
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Methods", "POST");  
        response.setHeader("Allow", "POST");  
		Map<String,String[]>params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&";  
            }  
        }  
		queryString = queryString.substring(0, queryString.length() - 1); 
		System.out.println("POST " + request.getRequestURL() + " " + queryString);
			return "123";  
	}  
	
//	 @RequestMapping(value="addjson", method = {RequestMethod.GET })  
//	    @ResponseBody  
//	    public String addUser(@RequestBody List<TestMenu> user,ServletRequest req, ServletResponse resp)  
//	    {   
//		 	HttpServletResponse response = (HttpServletResponse) resp;
////		 	response.setHeader("Access-Control-Allow-Origin","*");
////			response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
//			
//			
////		 	System.out.println("Hello");
////		 	
////			//			user.setName(user.getName());
//////			user.setPrice(user.getPrice());
////	        System.out.println(user.get(0).getName() + " " + user.get(0).getPrice()); 
////	        System.out.println("Hello");
//	        //return new HashMap<String, String>().put("success", "true");  
//	        return "123123";
//	    }  
	
//	@RequestMapping(value = "saveRestMsg", method = {RequestMethod.POST}) 
//    @ResponseBody  
//    public String saveRests1(@RequestBody List<Restaurant> rs, ServletRequest req, ServletResponse resp) throws IOException { 
//		HttpServletRequest request = (HttpServletRequest)req;
//		
//		HttpServletResponse response = (HttpServletResponse) resp;
//
//		Restaurant rest_add = rs.get(0);
//		Restaurant addR = new Restaurant();
//		String restname = rest_add.getName();
//		addR.setName(restname);
//		
//		String restloca = rest_add.getLocation();
//		addR.setLocation(restloca);
//		
//		String restintro = rest_add.getIntro();
//		addR.setIntro(restintro);
//		
//		String restphone = rest_add.getPhone();
//		addR.setPhone(restphone);
//		
//		String restsituation = rest_add.getSituation();
//		addR.setSituation(restsituation);
//		
//		String restdate = rest_add.getDate();
//		addR.setDate(restdate);
//		
//		String reststartnum = rest_add.getStartNum();
//		addR.setStartNum(reststartnum);
//		
//		String restendnum = rest_add.getEndNum();
//		addR.setEndNum(restendnum);
//		
//		String restprice = rest_add.getPrice();
//		addR.setPrice(restprice);
//		
//		String restemail = rest_add.getEmail();
//		addR.setEmail(restemail);
//		
//		String restrecomm = rest_add.getRecomm();
//		addR.setRecomm(restrecomm);
//		
//		String reststate = rest_add.getStature();
//		addR.setStature(reststate);
//		
//		String restlike = rest_add.getLike_rest();
//		addR.setLike_rest(restlike);
//		
//		String restfollow = rest_add.getFollow_rest();
//		addR.setFollow_rest(restfollow);
//		//String restimg = rest_add.getImg();
//		//System.out.print(restimg);
//		String restimg = rest_add.getImg().substring(23);
//		//image name
//		final long l = System.currentTimeMillis();
//		final int i = (int)( l % 1000 );
//		final int q = (int)( l % 1000 );
//		String path1 = request.getContextPath();
//		if(restimg == null || restimg.equals(null)){
//			String imgFilePath = request.getScheme() + "://" + request.getServerName() + ":"
//					+ request.getServerPort() + path1 + "/pic" + "/unknownuser.jpg";
//			addR.setImg(imgFilePath);
//		}else{
//			BASE64Decoder decoder = new BASE64Decoder();  
//			byte[] b = decoder.decodeBuffer(restimg);
//			String realpath = request.getSession().getServletContext().getRealPath("/pic"); 
//			 for(int j=0;j<b.length;j++)  
//	            {  
//	                if(b[j]<0)  
//	                {//调整异常数据  
//	                    b[j]+=256;  
//	                }  
//	            }  
//			 path1 = request.getContextPath();
//			 String imgFilePath = request.getScheme() + "://" + request.getServerName() + ":"
//						+ request.getServerPort() + path1 + "/pic" + "/abd.jpg";
//			 System.out.println("New:"+imgFilePath);
//			 
//			 
//	         addR.setImg(imgFilePath);
//		}
//		
//		
//		System.out.println("Restname:"+restname);
//		System.out.println("Location:"+restloca);
//		System.out.println("menu size:"+rs.get(0).getMenusrest().size());
//
//		//add menu to the restaurant
//		Iterator<MenuTable>it = rest_add.getMenusrest().iterator();
//		List<MenuTable> mtlist = new ArrayList<MenuTable>();
//		while(it.hasNext()){
//			mtlist.add(it.next());
//			
//		}
//		MenuTable mt = new MenuTable();
//		Set<MenuTable> mtable = new HashSet<MenuTable>();
//		for(int k = 0; k < mtlist.size(); k++){
//			
//			String m_name = mtlist.get(k).getName();
//			mt.setName(m_name);
//			
//			String m_descp = mtlist.get(k).getMenu_desc();
//			mt.setMenu_desc(m_descp);
//			
//			String m_meterial = mtlist.get(k).getMeterial();
//			mt.setMeterial(m_meterial);
//			mtable.add(mt);
//			addR.setMenusrest(mtable);
//		}
////		
////		//add manager to the restaurant
////		Iterator<ManagerRest>it_manage = rest_add.getManagersrest().iterator();
////		List<ManagerRest> managerlist = new ArrayList<ManagerRest>();
////		while(it_manage.hasNext()){
////			managerlist.add(it_manage.next());
////		}
////		ManagerRest mr = new ManagerRest();
////		Set<ManagerRest> mrset = new HashSet<ManagerRest>();
////		for(int f = 0; f<managerlist.size(); f++){
////			String man_name = managerlist.get(f).getName();
////			mr.setName(man_name);
////			
////			String man_pwd = managerlist.get(f).getPassword();
////			mr.setPassword(man_pwd);
////			
////			String man_nick = managerlist.get(f).getNickname();
////			mr.setPassword(man_nick);
////			
////			String man_whatup = managerlist.get(f).getWhatup();
////			mr.setWhatup(man_whatup);
////			
////			String man_email = managerlist.get(f).getEmail();
////			mr.setEmail(man_email);
////			
////			String man_phone = managerlist.get(f).getPhone();
////			mr.setPhone(man_phone);
////			
////			String man_sex = managerlist.get(f).getSex();
////			mr.setSex(man_sex);
////			
////			
////			String man_img = managerlist.get(f).getImg();
////			System.out.print(man_img);
////			if(man_img == null || man_img.equals(null)){
////				String imgFilePath = request.getScheme() + "://" + request.getServerName() + ":"
////						+ request.getServerPort() + path1 + "/pic" + "/unknownmanager.jpg";
////				mr.setImg(imgFilePath);
////			}else{
////				BASE64Decoder decoder = new BASE64Decoder();  
////				byte[] b = decoder.decodeBuffer(restimg);
////				realpath = request.getSession().getServletContext().getRealPath("/pic"); 
////				 for(int m=0;m<b.length;m++)  
////		            {  
////		                if(b[m]<0)  
////		                {//调整异常数据  
////		                    b[m]+=256;  
////		                }  
////		            }  
////				 path1 = request.getContextPath();
////				 String imgFilePath = request.getScheme() + "://" + request.getServerName() + ":"
////							+ request.getServerPort() + path1 + "/pic" + "/abcf.jpg";
////				 OutputStream out;
////				
////				 
////		         mr.setImg(imgFilePath);
////			}
////			mrset.add(mr);		
////			addR.setManagersrest(mrset);
////			
////			
////			
////		}
//		restService.add(addR);
//         return String.valueOf(rs.size());
//    }
	
}
