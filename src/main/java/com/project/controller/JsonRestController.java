package com.project.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopdiner.restaurant.MenuTable;
import com.loopdiner.restaurant.RestaurantService;
import com.loopdiner.restaurant.StoryRest;
import com.project.domain.ManagerRest;
import com.project.domain.Restaurant;
import com.project.fake.FakeRestaurant;
import com.project.model.ManagerRestModel;
import com.project.model.MenuModel;
import com.project.model.RestaurantModel;
import com.project.model.StroyRestModel;

@Controller
public class JsonRestController {

	private String path;
	private String realpath;

	private String path_sr;
	private String realpath_sr;

	@Resource(name = "restService")
	private RestaurantService restService;

	@Resource(name = "menuService")
	private MenuModel menuService;

	@Resource(name = "storyService")
	private StroyRestModel storyService;

	@Resource(name = "managerService")
	private ManagerRestModel managerService;

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

	public String getPath_sr() {
		return path_sr;
	}

	public void setPath_sr(String path_sr) {
		this.path_sr = path_sr;
	}

	public String getRealpath_sr() {
		return realpath_sr;
	}

	public void setRealpath_sr(String realpath_sr) {
		this.realpath_sr = realpath_sr;
	}

	@RequestMapping(value = "saveRestMsg", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveRests1(@RequestBody List<Restaurant> rs, ServletRequest req, ServletResponse resp)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) resp;

		Restaurant rest_add = rs.get(0);
		Restaurant addR = new Restaurant();
		String restname = rest_add.getName();
		Restaurant r_check = restService.queryName("Restaurant", restname);
		if (r_check != null) {
			System.out.println("该餐馆名已存在， 请更换");
			return "该餐馆名已存在， 请更换";
		} else {

			addR.setName(restname);

			String restloca = rest_add.getLocation();
			addR.setLocation(restloca);

			String restintro = rest_add.getIntro();
			addR.setIntro(restintro);

			String restphone = rest_add.getPhone();
			addR.setPhone(restphone);

			String restsituation = rest_add.getSituation();
			addR.setSituation(restsituation);

			String restdate = rest_add.getDate();
			addR.setDate(restdate);

			String reststartnum = rest_add.getStartNum();
			addR.setStartNum(reststartnum);

			String restendnum = rest_add.getEndNum();
			addR.setEndNum(restendnum);

			String restprice = rest_add.getPrice();
			addR.setPrice(restprice);

			String restemail = rest_add.getEmail();
			addR.setEmail(restemail);

			String restrecomm = rest_add.getRecomm();
			addR.setRecomm(restrecomm);

			String reststate = rest_add.getStature();
			addR.setStature(reststate);

			String restlike = rest_add.getLike_rest();
			addR.setLike_rest(restlike);

			String restfollow = rest_add.getFollow_rest();
			addR.setFollow_rest(restfollow);

			String restmanagerid = rest_add.getManagerid();
			addR.setManagerid(restmanagerid);

			String restimg = rest_add.getImg();
			if (restimg == null || restimg.equals(null) || restimg.length() <= 0) {
				addR.setImg(null);
			} 
			else {

				restimg = rest_add.getImg().substring(23);
				// image name

				String path1 = request.getContextPath();
				String picname = UUID.randomUUID().toString();

				BASE64Decoder decoder = new BASE64Decoder();
				byte[] b = decoder.decodeBuffer(restimg);
				realpath = request.getSession().getServletContext().getRealPath("/pic");
				for (int j = 0; j < b.length; j++) {
					if (b[j] < 0) {// 调整异常数据
						b[j] += 256;
					}
				}
				path = realpath + "/" + picname + ".jpg";
				// File localFile = new File(path);
				OutputStream out = new FileOutputStream(path);
				out.write(b);
				out.flush();
				out.close();

				path1 = request.getContextPath();
				String imgFilePath = request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + path1 + "/pic/" + picname + ".jpg";

				System.out.println("New:" + imgFilePath);
				// File localFile = new File(path);
				addR.setImg(imgFilePath);
			}
		}
		System.out.println("Restname:" + restname);
		// System.out.println("Location:" + restloca);
		//System.out.println("menu size:" + rs.get(0).getMenusrest().size());
		restService.add(addR);
		Restaurant r_new = restService.queryName("Restaurant", restname);
		if (r_new != null) {
			System.out.println("RName:" + r_new.getName());
		} else {
			System.out.println("NULL");
		}

		// add menu to the restaurant
		Iterator<MenuTable> it = rest_add.getMenus().iterator();
		List<MenuTable> mtlist = new ArrayList<MenuTable>();
		while (it.hasNext()) {
			mtlist.add(it.next());

		}
		MenuTable mt = new MenuTable();
		// List<MenuTable> mtable = new ArrayList<MenuTable>();
		// Set<MenuTable> mtable = new HashSet<MenuTable>();
		for (int k = 0; k < mtlist.size(); k++) {
			System.out.println("MENU:" + mtlist.get(k).getName());
			String m_name = mtlist.get(k).getName();
			mt.setName(m_name);

			String m_descp = mtlist.get(k).getMenu_desc();
			mt.setMenu_desc(m_descp);

			String m_meterial = mtlist.get(k).getMaterial();
			mt.setMaterial(m_meterial);

			String m_state = mtlist.get(k).getStature();
			mt.setStature(m_state);

			//mt.setRest_menu(r_new);
			// mtable.add(mt);
			menuService.add(mt);
		}

		Iterator<StoryRest> itr = rest_add.getStorys().iterator();
		List<StoryRest> srlist = new ArrayList<StoryRest>();

		while (itr.hasNext()) {
			srlist.add(itr.next());
		}
		System.out.print(srlist.size());

		for (int k = 0; k < srlist.size(); k++) {
			StoryRest sr = new StoryRest();
			String sr_content = srlist.get(k).getStory_content();
			sr.setStory_content(sr_content);

			//sr.setRest_story(r_new);
			String sr_img = srlist.get(k).getImg();
			if (sr_img.length() <= 0 || sr_img == null || sr_img.equals(null)) {
				sr.setImg(null);
			} else {
				sr_img = srlist.get(k).getImg().substring(23);
				System.out.println("IMG:" + sr_img);
				String picnamers = UUID.randomUUID().toString();

				BASE64Decoder decoder_sr = new BASE64Decoder();
				byte[] b_sr = decoder_sr.decodeBuffer(sr_img);
				realpath_sr = request.getSession().getServletContext().getRealPath("/pic");
				for (int n = 0; n < b_sr.length; n++) {
					if (b_sr[n] < 0) {// 调整异常数据
						b_sr[n] += 256;
					}
				}
				path_sr = realpath_sr + "/" + picnamers + ".png";
				// File localFile = new File(path);
				OutputStream out = new FileOutputStream(path_sr);
				out.write(b_sr);
				out.flush();
				out.close();

				String path1 = request.getContextPath();
				String imgFilePath_sr = request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + path1 + "/pic/" + picnamers + ".png";

				System.out.println("New:" + imgFilePath_sr);
				// File localFile = new File(path);
				sr.setImg(imgFilePath_sr);
			}
			storyService.add(sr);
		}

		// add manager to the restaurant
		// Iterator<ManagerRest>it_manage =
		// rest_add.getManagersrest().iterator();
		// List<ManagerRest> managerlist = new ArrayList<ManagerRest>();
		// while(it_manage.hasNext()){
		// managerlist.add(it_manage.next());
		// }
		// ManagerRest mr = new ManagerRest();
		// Set<ManagerRest> mrset = new HashSet<ManagerRest>();
		// for(int f = 0; f<managerlist.size(); f++){
		// String man_name = managerlist.get(f).getName();
		// mr.setName(man_name);
		//
		// String man_pwd = managerlist.get(f).getPassword();
		// mr.setPassword(man_pwd);
		//
		// String man_nick = managerlist.get(f).getNickname();
		// mr.setPassword(man_nick);
		//
		// String man_whatup = managerlist.get(f).getWhatup();
		// mr.setWhatup(man_whatup);
		//
		// String man_email = managerlist.get(f).getEmail();
		// mr.setEmail(man_email);
		//
		// String man_phone = managerlist.get(f).getPhone();
		// mr.setPhone(man_phone);
		//
		// String man_sex = managerlist.get(f).getSex();
		// mr.setSex(man_sex);
		//
		//
		// String man_img = managerlist.get(f).getImg();
		// System.out.print(man_img);
		// if(man_img == null || man_img.equals(null)){
		// String imgFilePath = request.getScheme() + "://" +
		// request.getServerName() + ":"
		// + request.getServerPort() + path1 + "/pic" +
		// "/unknownmanager.jpg";
		// mr.setImg(imgFilePath);
		// }else{
		// BASE64Decoder decoder = new BASE64Decoder();
		// byte[] b = decoder.decodeBuffer(restimg);
		// realpath =
		// request.getSession().getServletContext().getRealPath("/pic");
		// for(int m=0;m<b.length;m++)
		// {
		// if(b[m]<0)
		// {//调整异常数据
		// b[m]+=256;
		// }
		// }
		// path1 = request.getContextPath();
		// String imgFilePath = request.getScheme() + "://" +
		// request.getServerName() + ":"
		// + request.getServerPort() + path1 + "/pic" + "/abcf.jpg";
		// OutputStream out;
		//
		//
		// mr.setImg(imgFilePath);
		// }
		// mrset.add(mr);
		// addR.setManagersrest(mrset);

		// }

		return String.valueOf(rs.size());
	}

	

	

	// @RequestMapping(value = "querySpecRest", method = {RequestMethod.GET})
	// @ResponseBody
	// public Map<String, Restaurant>
	// saveUser(@RequestParam(value="id_rest",required=false) int id,
	// HttpServletRequest request ) throws JsonProcessingException {
	// System.out.print(id);
	// Restaurant restaurant = restService.queryOne("Restaurant", id);
	// System.out.println(restaurant.getName());
	// Map<String, Restaurant> map = new HashMap<String, Restaurant>();
	// map.put("rest_detail", restaurant);
	// return map;
	//
	// }
	@RequestMapping(value = "querySpecRest", method = { RequestMethod.GET }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRestByIds(@RequestParam(value = "id_rest", required = false) int id, HttpServletRequest request,
			Model model) throws JsonProcessingException {
		System.out.print(id);
		Restaurant restaurant = restService.queryOne("Restaurant", id);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("name", restaurant.getName());
		model.addAttribute("id", restaurant.getId());
		model.addAttribute("img", restaurant.getImg());
		model.addAttribute("intro", restaurant.getIntro());
		int man_id = Integer.parseInt(restaurant.getManagerid());
		ManagerRest manager_rest = managerService.queryOne("ManagerRest", man_id);
		if (manager_rest != null) {
			model.addAttribute("manager_img", manager_rest.getImg());
			model.addAttribute("manager_id", manager_rest.getId());
		} else {
			model.addAttribute("manager_img", null);
			model.addAttribute("manager_id", null);
		}

		String rest_location = restaurant.getLocation();
		System.out.println("Locate:" + rest_location);
		String[] result = rest_location.split(",");
		List<String> result_list = new ArrayList<String>();
		for (int j = 0; j < result.length; j++) {
			System.out.println(result[j]);
			String[] final_result = result[j].split(":");
			result_list.add(final_result[1]);
		}
		for (int j = 0; j < result_list.size(); j++) {
			System.out.println(result_list.get(j));
		}
		model.addAttribute("street1", result_list.get(0));
		model.addAttribute("street2", result_list.get(1));
		model.addAttribute("sub", result_list.get(2));
		model.addAttribute("state", result_list.get(3));
		model.addAttribute("postcode", result_list.get(4));
		String Json = mapper.writeValueAsString(model);
		return Json;

	}

	
	
	@RequestMapping(value = "queryRecommRest", method = { RequestMethod.GET }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRestByRecomm(HttpServletRequest request, Model model) throws JsonProcessingException {

		List<Restaurant>restRecommList = restService.queryPopularRest("true");
		System.out.println("restRecommList.size():"+restRecommList.size());
		if(restRecommList.size()<=0){
			return null;
		}else{
			
			List<String>finalJsonStr = new ArrayList<String>();
			ObjectMapper mapper = new ObjectMapper();
			List<Model>mlist = new ArrayList<Model>();
			List<FakeRestaurant> frlist = new ArrayList<FakeRestaurant>();
			for(int i = 0; i < restRecommList.size(); i++){
				FakeRestaurant f_rest = new FakeRestaurant();
				Restaurant restaurant = restRecommList.get(i);
				System.out.println(restaurant.getName());
//				model.addAttribute("name", restaurant.getName());
//				model.addAttribute("id", restaurant.getId());
//				model.addAttribute("img", restaurant.getImg());
//				model.addAttribute("intro", restaurant.getIntro());
				
				f_rest.setName(restaurant.getName());
				f_rest.setId(restaurant.getId());
				f_rest.setImg(restaurant.getImg());
				f_rest.setIntro(restaurant.getIntro());
				int man_id = Integer.parseInt(restaurant.getManagerid());
				ManagerRest manager_rest = managerService.queryOne("ManagerRest", man_id);
				if (manager_rest != null) {
//					model.addAttribute("manager_img", manager_rest.getImg());
//					model.addAttribute("manager_id", manager_rest.getId());
					
					f_rest.setManager_id(man_id);
					f_rest.setManager_img(manager_rest.getImg());
				} else {
//					model.addAttribute("manager_img", null);
//					model.addAttribute("manager_id", null);
					
					f_rest.setManager_id(0);
					f_rest.setManager_img(null);
				}

				String rest_location = restaurant.getLocation();
				System.out.println("Locate:" + rest_location);
				String[] result = rest_location.split(",");
				List<String> result_list = new ArrayList<String>();
				for (int j = 0; j < result.length; j++) {
					System.out.println(result[j]);
					String[] final_result = result[j].split(":");
					if(final_result[1].length()<=0 || final_result[1] ==null){
						result_list.add(null);
					}else{
						result_list.add(final_result[1]);
						
					}
					
				}
				for (int j = 0; j < result_list.size(); j++) {
					System.out.println(result_list.get(j));
				}
//				model.addAttribute("street1", result_list.get(0));
//				model.addAttribute("street2", result_list.get(1));
//				model.addAttribute("sub", result_list.get(2));
//				model.addAttribute("state", result_list.get(3));
//				model.addAttribute("postcode", result_list.get(4));
//				mlist.add(model);
//				String Json = mapper.writeValueAsString(model);
//				finalJsonStr.add(Json);
				
				f_rest.setStreet1(result_list.get(0));
				f_rest.setStreet2(result_list.get(1));
				f_rest.setSub(result_list.get(2));
				f_rest.setState(result_list.get(3));
				f_rest.setPostcode(result_list.get(4));
				
				frlist.add(f_rest);
			}
//			int size_arr = finalJsonStr.size();
//			String []jsonStr = new String[size_arr];
//			for (int g = 0; g<size_arr ;g++){
//				jsonStr[g] = finalJsonStr.get(g);
//			}
			 //JSONArray jsArr = JSONArray  
			
			
			
			//String returnJson = mapper.writeValueAsString(finalJsonStr);
			//JSONArray.toJSONString(finalJsonStr);
		    //JSONArray.fromObject(finalJsonStr).toString();
//			String[] stockArr = new String[finalJsonStr.size()];
//			stockArr = finalJsonStr.toArray(stockArr);
			return mapper.writeValueAsString(frlist);
			
		}
	}

}
