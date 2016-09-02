package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loopdiner.restaurant.MenuTable;
import com.loopdiner.restaurant.RestaurantService;
import com.project.domain.Restaurant;
import com.project.model.MenuModel;
import com.project.model.RestaurantModel;
import com.project.util.HibernatePage;
import com.project.util.PagesDomain;

@Controller
public class MenuController {
	@Resource(name = "menuService")
	private MenuModel menuService;
	
	@Resource(name = "restService")
	private RestaurantService restService;
	
	private HibernatePage hp = new HibernatePage();
	
	private String path;
	private String realpath;
	private String fileName;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRealpath() {
		return realpath;
	}
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public HibernatePage getHp() {
		return hp;
	}
	public void setHp(HibernatePage hp) {
		this.hp = hp;
	}
	@RequestMapping(value = "menuslist", method = RequestMethod.GET)
	public String menuList(@RequestParam(value = "rid", required = false) int id,HttpServletRequest request){
		int pagesize_menu = 2;
		String hql = "select a from com.project.domain.MenuTable a where a.rest_menu.id="+id;
		List<MenuTable> menus = menuService.h_Page(hql, 0, pagesize_menu);
		request.getSession().setAttribute("menus", menus);
		int cpage_menu = 1;
		request.getSession().setAttribute("cpage_menu", cpage_menu);
		request.getSession().setAttribute("pagesize_menu", pagesize_menu);
		request.getSession().setAttribute("menurestid", id);
		return "menulist";
	}
	
	@RequestMapping(value = "pagemenu", method = RequestMethod.GET)
	public String upPage(@RequestParam(value = "cpage_menu", required = false) int cpage, HttpServletRequest request) {
		PagesDomain pd = new PagesDomain();
		int id = (Integer) request.getSession().getAttribute("menurestid");
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_rest");
		List<MenuTable> totalrests = menuService.queryAll("select a from com.project.domain.MenuTable a where a.rest_menu.id="+id);
		int count = totalrests.size();
		pd = hp.turnPage(cpage, pagesize, count);
		
		request.getSession().setAttribute("cpage_menu", pd.getCpage());
		

		List<MenuTable> menus = menuService.h_Page("select a from com.project.domain.MenuTable a where a.rest_menu.id="+id, pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("menus", menus);
		return "menulist";
	}
	
	@RequestMapping(value = "/viewmenu", method = RequestMethod.GET)
	public String viewRestDetail(@RequestParam(value = "mid", required = false) int id, HttpServletRequest request) {
		MenuTable mt = menuService.queryOne("MenuTable", id);
		request.getSession().setAttribute("menudetails", mt);
		return "menudetail";
	}
	
	@RequestMapping(value = "/updatemenu", method = RequestMethod.GET)
	public String updateMenuDetail(@RequestParam(value = "mid", required = false) int id, HttpServletRequest request){
		MenuTable mt = menuService.queryOne("MenuTable", id);
		request.getSession().setAttribute("menudetails", mt);
		return "menuupdate";
	}
	@RequestMapping(value = "/confirmupdatemenu", method = RequestMethod.POST)
	public ModelAndView confirmUpdateMenuDetail(@RequestParam("file") MultipartFile file,@RequestParam(value = "rid", required = false) int id, HttpServletRequest request, RedirectAttributes attributes) throws IllegalStateException, IOException{
		MenuTable mt = (MenuTable)request.getSession().getAttribute("menudetails");
		MenuTable mt_up = new MenuTable();
		mt_up.setId(mt.getId());
		mt_up.setOrdernumber(mt.getOrdernumber());
		if(!file.isEmpty()){
			System.out.println("Yes");
				//realpath = "D:/workspace-sts-3.7.2.RELEASE/Test/src/main/webapp/pic";
			    realpath = request.getSession().getServletContext().getRealPath("/pic"); 

				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
						request.getSession().getServletContext());

				if (multipartResolver.isMultipart(request)) {

					MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
					Iterator iter = multiRequest.getFileNames();
					if (iter.hasNext()) {
						MultipartFile file1 = multiRequest.getFile((String) iter.next());
						if (file1 != null) {
							fileName = file1.getOriginalFilename();
							path = realpath + "/" + file1.getOriginalFilename();
							// System.out.println("path"+path);
							File localFile = new File(path);
							file1.transferTo(localFile);
						}
						String path1 = request.getContextPath();
						System.out.println("old:"+request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + path1 + "/pic" + "/" + fileName);
						mt_up.setImg_menu(request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + path1 + "/pic" + "/" + fileName);
					} 
				}

			}else{
				
				mt_up.setImg_menu("http://localhost:8080/server/pic/nopic.jpeg");
//				if(mt_up.getImg_menu() == ""||mt_up.getImg_menu().equals(null)||mt_up.getImg_menu().length()<=0 || mt_up.getImg_menu() == null ){
//					mt_up.setImg_menu("http://localhost:8080/server/pic/nopic.jpeg");
//				}else{
//					mt_up.setImg_menu(mt_up.getImg_menu());
//				}			
			}
		//int menurestid = (Integer) request.getSession().getAttribute("menurestid");
		Restaurant r = restService.queryOne("Restaurant", id);
		mt_up.setName(request.getParameter("name"));
		mt_up.setMenu_price(request.getParameter("price"));
		mt_up.setMenu_desc(request.getParameter("desc"));
		//mt_up.setRest_menu(r);
		
		menuService.update(mt_up);
		//return "redirect:/menuslist?rid="menurestid";
		
		return new ModelAndView("redirect:/menuslist?rid="+id);
		
	}
	@RequestMapping(value="/deletemenu", method=RequestMethod.GET)
	public ModelAndView deleteRest(@RequestParam(value = "mid", required = false) int mid, HttpServletRequest request){
		MenuTable m = menuService.queryOne("MenuTable", mid);
		Restaurant re = (Restaurant) request.getSession().getAttribute("restdetails");
		if(m != null){
			menuService.del(m);
		}
		return new ModelAndView("redirect:/menuslist?rid="+re.getId());
	}
	
}
