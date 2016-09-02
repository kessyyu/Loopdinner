package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopdiner.restaurant.RestaurantService;
import com.project.domain.Restaurant;
import com.project.domain.User;
import com.project.model.RestaurantModel;
import com.project.util.HibernatePage;
import com.project.util.PagesDomain;

@Controller
public class RestaurantController {
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

	@RequestMapping(value = "restslist", method = RequestMethod.GET)
	public String restList(HttpServletRequest request) {
		int pagesize_rest = 2;
		List<Restaurant> rests = restService.h_Page("from com.project.domain.Restaurant", 0, pagesize_rest);
		request.getSession().setAttribute("rests", rests);
		int cpage_rest = 1;
		request.getSession().setAttribute("cpage_rest", cpage_rest);
		request.getSession().setAttribute("pagesize_rest", pagesize_rest);
		return "restlist";
	}

	@RequestMapping(value = "pagerest", method = RequestMethod.GET)
	public String upPage(@RequestParam(value = "cpage_rest", required = false) int cpage, HttpServletRequest request) {
		PagesDomain pd = new PagesDomain();
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_rest");
		List<Restaurant> totalrests = restService.queryAll("from com.project.domain.Restaurant");
		int count = totalrests.size();
		pd = hp.turnPage(cpage, pagesize, count);
		
		request.getSession().setAttribute("cpage_rest", pd.getCpage());
		

		List<Restaurant> rests = restService.h_Page("from com.project.domain.Restaurant", pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("rests", rests);
		return "restlist";
	}

	@RequestMapping(value = "/viewrest", method = RequestMethod.GET)
	public String viewRestDetail(@RequestParam(value = "rid", required = false) int id, HttpServletRequest request) {
		Restaurant rest = restService.queryOne("Restaurant", id);
		request.getSession().setAttribute("restdetails", rest);
		return "restdetail";
	}

	@RequestMapping(value = "/updaterest", method = RequestMethod.GET)
	public String updateRestDetail(@RequestParam(value = "rid", required = false) int id, HttpServletRequest request) {
		Restaurant rest = restService.queryOne("Restaurant", id);
		request.getSession().setAttribute("restdetails", rest);
		return "restupdate";
	}

	@RequestMapping(value = "/confirmupdaterest", method = RequestMethod.POST)
	public String confirmRestaurantUpdate(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		Restaurant rest = (Restaurant) request.getSession().getAttribute("restdetails");
		Restaurant restupdate = new Restaurant();
		restupdate.setId(rest.getId());
		// System.out.println("Filename:"+myfile.getName());
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
						 System.out.println("path:"+path);
						File localFile = new File(path);
						file1.transferTo(localFile);
					}
					String path1 = request.getContextPath();
					restupdate.setImg(request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + path1 + "/pic" + "/" + fileName);
				} 
			}

		}else{
			System.out.println("No");
			restupdate.setImg("http://localhost:8080/server/pic/nopic.jpeg");
//			if(rest.getImg() == null || rest.getImg().equals(null)||rest.getImg().length()<=0){
//				restupdate.setImg("http://localhost:8080/server/pic/nopic.jpeg");
//			}else{
//				restupdate.setImg(rest.getImg());
//			}
			
		}

		// //String myappPath =
		// request.getSession().getServletContext().getRealPath("/");
		//

		restupdate.setName(request.getParameter("name"));
		restupdate.setPhone(request.getParameter("phone"));
		restupdate.setLocation(request.getParameter("location"));
		restupdate.setIntro(request.getParameter("description"));
		restupdate.setSituation(request.getParameter("otherinfo"));
		restService.update(restupdate);
		return "redirect:/restslist";
	}
	
	@RequestMapping(value="/deleterest", method=RequestMethod.GET)
	public String deleteRest(@RequestParam(value = "rid", required = false) int id, HttpServletRequest request){
		Restaurant rest = restService.queryOne("Restaurant", id);
		if(rest != null){
			restService.del(rest);
		}
		return "redirect:/restslist";
	}
	
	@RequestMapping(value="/searchrest",method=RequestMethod.POST)
	public String searchByRestaurant(HttpServletRequest request){
		int pagesize = 2;
		String searchtext = request.getParameter("restnamesearch");
		List<Restaurant> restresult = restService.search("Restaurant", searchtext, 0, pagesize);

		request.getSession().setAttribute("restnamesearch", restresult);
		int cpage_restsear = 1;
		request.getSession().setAttribute("cpage_restsear", cpage_restsear);
		request.getSession().setAttribute("pagesize_rest_sear", pagesize);
		request.getSession().setAttribute("searchtextrest", searchtext);
		return "restlistsearch";
	}
	
	@RequestMapping(value="pagerestsearch", method=RequestMethod.GET)
	public String restSearchTurnPage(@RequestParam(value="cpage_restsear",required=false)  int cpage, HttpServletRequest request){
		PagesDomain pd = new PagesDomain();
		String searchtext = (String) request.getSession().getAttribute("searchtextrest");
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_rest_sear");
		List<Restaurant>totalrestsearch = restService.searchTotals("Restaurant", searchtext);
		int count = totalrestsearch.size();
		//System.out.println("Count"+count);
		pd = hp.turnPage(cpage, pagesize, count);

		request.getSession().setAttribute("cpage_restsear", pd.getCpage());
		List<Restaurant>restnamesearch = restService.search("Restaurant", searchtext, pd.getFrom_page(),  pagesize);
		request.getSession().setAttribute("restnamesearch", restnamesearch);
		return "restlistsearch";
	}
	
	/*
	 * add restaurant
	 */
	@RequestMapping(value = "/addrest", method = RequestMethod.GET)
	public String addRestaurant(@RequestParam(value = "name", required = false) String name,@RequestParam(value = "img", required = false) String img, @RequestParam(value = "intro", required = false) String intro,ServletRequest req, ServletResponse resp) throws IllegalStateException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		
		
		
		//Restaurant rest = (Restaurant) request.getSession().getAttribute("restdetails");
		Restaurant restadd = new Restaurant();
		
		// System.out.println("Filename:"+myfile.getName());
		
		System.out.println("Yes");
			//realpath = "D:/workspace-sts-3.7.2.RELEASE/Test/src/main/webapp/pic";
			realpath = request.getSession().getServletContext().getRealPath("/pic"); 

//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//					request.getSession().getServletContext());

			
			String path1 = request.getContextPath();
			restadd.setImg(request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + path1 + "/pic" + "/" + img);
		

		// //String myappPath =
		// request.getSession().getServletContext().getRealPath("/");
		//

		restadd.setName(request.getParameter("name"));
		
		restadd.setIntro(intro);
		
		
		restService.add(restadd);
		
		ObjectMapper mapper = new ObjectMapper();  
		String Json =  mapper.writeValueAsString(restadd); 
		
		return Json;
	}
}
