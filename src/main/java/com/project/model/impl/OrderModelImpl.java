package com.project.model.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.OrderDao;
import com.project.domain.Order;
import com.project.model.OrderModel;

@Service(value="orderService")
public class OrderModelImpl extends BaseModelImpl<Order> implements OrderModel{

	@Resource(name="orderDao")
	private OrderDao orderDao;
}
