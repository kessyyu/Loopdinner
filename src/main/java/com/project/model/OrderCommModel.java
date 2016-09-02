package com.project.model;

import java.util.List;

import com.project.domain.OrderComm;

public interface OrderCommModel extends BaseModel<OrderComm>{
	public List<OrderComm> queryOrderCommByUser(int uid);
	public List<OrderComm> queryOrderCommByRestaurant(int rid);
	public List<OrderComm> queryOrderCommByOrder(int oid);

}
