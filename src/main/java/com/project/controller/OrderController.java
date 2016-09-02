package com.project.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.domain.Order;
import com.project.domain.OrderComm;
import com.project.domain.Restaurant;
import com.project.domain.User;
import com.project.model.OrderCommModel;
import com.project.model.OrderModel;
import com.project.util.HibernatePage;
import com.project.util.PagesDomain;

@Controller
public class OrderController {
	@Resource(name="orderService")
	private OrderModel orderService;
	
	@Resource(name="ordercommService")
	private OrderCommModel ordercommService;
	
	private HibernatePage hp = new HibernatePage();
	
	
	public HibernatePage getHp() {
		return hp;
	}
	public void setHp(HibernatePage hp) {
		this.hp = hp;
	}
	@RequestMapping(value="restorder", method=RequestMethod.GET)
	public String restaurantOrder(@RequestParam(value = "rid", required = false) int id,HttpServletRequest request){
		int pagesize_rest_order = 2;
		String hql = "select a from com.project.domain.Order a where a.rest_order.id="+id;
		List<Order> orders = orderService.h_Page(hql, 0, pagesize_rest_order);
		request.getSession().setAttribute("orderinrest", orders);
		int cpage_orderrest = 1;
		request.getSession().setAttribute("cpage_orderrest", cpage_orderrest);
		request.getSession().setAttribute("pagesize_rest_order", pagesize_rest_order);
		request.getSession().setAttribute("orderrestid", id);
		return "orderstorestaurant";
	}
	@RequestMapping(value="pagerestorder", method=RequestMethod.GET)
	public String pageRestOrder(@RequestParam(value = "cpage_orderrest", required = false) int cpage, HttpServletRequest request){
		PagesDomain pd = new PagesDomain();
		int id = (Integer) request.getSession().getAttribute("orderrestid");
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_rest_order");
		List<Order> totalrestorders = orderService.queryAll("select a from com.project.domain.Order a where a.rest_order.id="+id);
		int count = totalrestorders.size();
		pd = hp.turnPage(cpage, pagesize, count);


		request.getSession().setAttribute("cpage_orderrest", pd.getCpage());

		List<Order> orderinrest = orderService.h_Page("select a from com.project.domain.Order a where a.rest_order.id="+id, pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("orderinrest", orderinrest);
		return "orderstorestaurant";
	}
	
	@RequestMapping(value="userorderinfo", method=RequestMethod.GET)
	public String userOrders(@RequestParam(value = "urid", required = false) int id,HttpServletRequest request){
		int pagesize_user_order = 2;
		String hql = "select a from com.project.domain.Order a where a.user_order.id="+id;
		List<Order> orders = orderService.h_Page(hql, 0, pagesize_user_order);
		request.getSession().setAttribute("orderinuser", orders);
		int cpage_orderuser = 1;
		request.getSession().setAttribute("cpage_orderuser", cpage_orderuser);
		request.getSession().setAttribute("pagesize_rest_user", pagesize_user_order);
		request.getSession().setAttribute("orderuserid", id);
		return "orderstouser";
	}
	
	@RequestMapping(value="pageuserorder", method=RequestMethod.GET)
	public String pageUserOrder(@RequestParam(value = "cpage_orderuser", required = false) int cpage, HttpServletRequest request){
		PagesDomain pd = new PagesDomain();
		int id = (Integer) request.getSession().getAttribute("orderuserid");
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_rest_user");
		List<Order> totaluserorders = orderService.queryAll("select a from com.project.domain.Order a where a.user_order.id="+id);
		int count = totaluserorders.size();
		pd = hp.turnPage(cpage, pagesize, count);
		
		
		request.getSession().setAttribute("cpage_orderuser", pd.getCpage());

		List<Order> orderinuser = orderService.h_Page("select a from com.project.domain.Order a where a.user_order.id="+id, pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("orderinuser", orderinuser);
		return "orderstouser";
	}
	@RequestMapping(value="/deleterestorder", method=RequestMethod.GET)
	public ModelAndView deleteRestOrder(@RequestParam(value = "roid", required = false) int id, HttpServletRequest request){
		Order order = orderService.queryOne("Order", id);
		Restaurant re = (Restaurant) request.getSession().getAttribute("restdetails");
		if(order != null){
			orderService.del(order);
		}
		return new ModelAndView("redirect:/restorder?rid="+re.getId());
	}
	
	@RequestMapping(value="/deleteuserorder", method=RequestMethod.GET)
	public ModelAndView deleteuserOrder(@RequestParam(value = "uoid", required = false) int id, HttpServletRequest request){
		Order order = orderService.queryOne("Order", id);
		User u = (User) request.getSession().getAttribute("userdetails");
		
		if(order != null){
			orderService.del(order);
		}
		return new ModelAndView("redirect:/userorderinfo?urid="+u.getId());
	}
	/*
	 * View signal restaurant's order
	 */
	
	@RequestMapping(value="/viewrestorder", method=RequestMethod.GET)
	public String viewRestOrder(@RequestParam(value = "roid", required = false) int id, HttpServletRequest request){
		Order order = orderService.queryOne("Order", id);
		request.getSession().setAttribute("orderdetail", order);
		List<OrderComm> listordercomm_rest = ordercommService.queryAll("select u from com.project.domain.OrderComm u where u.ordertocomm.id=" + id);
		request.getSession().setAttribute("listordercomm_rest", listordercomm_rest);
		return "orderrest_detail";
	}
	
	/*
	 * View signal user's order
	 */
	
	@RequestMapping(value="/viewuserorder", method=RequestMethod.GET)
	public String viewUserOrder(@RequestParam(value = "uoid", required = false) int id, HttpServletRequest request){
		Order order = orderService.queryOne("Order", id);
		request.getSession().setAttribute("userorderdetail", order);
		List<OrderComm> listordercomm_user = ordercommService.queryAll("select u from com.project.domain.OrderComm u where u.ordertocomm.id=" + id);
		request.getSession().setAttribute("listordercomm_user", listordercomm_user);
		return "orderuser_detail";
	}
	
	/*
	 * View all orders
	 */
	
	@RequestMapping(value="/viewallorders", method=RequestMethod.GET)
	public String viewAllOrders(HttpServletRequest request){
		int pagesize_orders = 2;
		List<Order>orders = orderService.h_Page("from com.project.domain.Order", 0, pagesize_orders);
		request.getSession().setAttribute("allorders", orders);
		int cpage = 1;
		request.getSession().setAttribute("cpage_allorders", cpage);
		request.getSession().setAttribute("pagesize_orders", pagesize_orders);
		return "allorderlist";
	}
	
	@RequestMapping(value="pageorder", method=RequestMethod.GET)
	public String upPage(@RequestParam(value="cpage_allorders",required=false)  int cpage, HttpServletRequest request){
		PagesDomain pd = new PagesDomain();
		int pagesize = (Integer) request.getSession().getAttribute("pagesize_orders");
		List<Order>totalorders = orderService.queryAll("from com.project.domain.Order");
		int count = totalorders.size();
		pd = hp.turnPage(cpage, pagesize, count);

		request.getSession().setAttribute("cpage_allorders", pd.getCpage());
		 
		List<Order>orders = orderService.h_Page("from com.project.domain.Order", pd.getFrom_page(), pagesize);
		request.getSession().setAttribute("allorders", orders);
		return "allorderlist";
	}
	
	@RequestMapping(value="/viewallorderdetail", method=RequestMethod.GET)
	public String showSingleOrder(@RequestParam(value = "oid", required = false) int id, HttpServletRequest request){
		Order order = orderService.queryOne("Order", id);
		request.getSession().setAttribute("orderdetails", order);
		List<OrderComm> listordercomm_order = ordercommService.queryAll("select u from com.project.domain.OrderComm u where u.ordertocomm.id=" + id);
		request.getSession().setAttribute("listordercomm_order", listordercomm_order);
		return "order_detail";
	}
	
	
	
}
