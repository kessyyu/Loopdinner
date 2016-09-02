package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.OrderDao;
import com.project.domain.Order;
@Repository(value="orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

}
