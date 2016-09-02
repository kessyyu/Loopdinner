package com.project.dao;

import java.util.List;

import com.project.domain.OrderComm;

public interface OrderCommDao extends BaseDao<OrderComm>{
	public List<OrderComm> queryOrderCommByUser(int uid);
	public List<OrderComm> queryOrderCommByRestaurant(int rid);
	public List<OrderComm> queryOrderCommByOrder(int oid);

}
