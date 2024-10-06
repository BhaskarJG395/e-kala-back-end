package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.OrderDao;
import com.app.dto.request.OrderReqDto;
import com.app.entities.OrderEntity;
import com.app.services.interfaces.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public String addOrder(OrderReqDto orderReqDto) {
		// TODO Auto-generated method stub
		OrderEntity order = mapper.map(orderReqDto, OrderEntity.class);
		orderDao.save(order);
		return "saved";
	}

}
