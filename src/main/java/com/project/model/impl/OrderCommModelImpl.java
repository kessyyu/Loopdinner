package com.project.model.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.OrderCommDao;
import com.project.model.OrderCommModel;
import com.project.domain.OrderComm;
@Service(value="ordercommService")

public class OrderCommModelImpl extends BaseModelImpl<OrderComm> implements OrderCommModel{
	@Resource(name="ordercommDao")
	private OrderCommDao ordercommDao;

	@Override
	public List<OrderComm> queryOrderCommByUser(int uid) {
		
		return ordercommDao.queryOrderCommByUser(uid);
	}

	@Override
	public List<OrderComm> queryOrderCommByRestaurant(int rid) {

		return ordercommDao.queryOrderCommByRestaurant(rid);
	}

	@Override
	public List<OrderComm> queryOrderCommByOrder(int oid) {

		return ordercommDao.queryOrderCommByOrder(oid);
	}
}
