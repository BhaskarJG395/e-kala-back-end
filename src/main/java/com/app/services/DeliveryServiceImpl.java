package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.CustomExcp;
import com.app.dao.DeliveryDao;
import com.app.dao.PaymentDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.DeliveryRespDto;
import com.app.dto.PaymentRespDto;
import com.app.dto.request.DeliveryReqDto;
import com.app.entities.DeliveryEntity;
import com.app.entities.PaymentEntity;
import com.app.entities.StatusEnum;
import com.app.entities.UserEntity;
import com.app.services.interfaces.DeliveryServices;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryServices {
	@Autowired
	private DeliveryDao deliveryDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<DeliveryRespDto> getAllDelivery() {
		
		return deliveryDao.findAll().stream().map(e->mapper.map(e, DeliveryRespDto.class)).collect(Collectors.toList());
	}

	@Override
	public DeliveryRespDto getDeliveryByUser(Long userId) {
		UserEntity user=userDao.findById(userId).orElseThrow(()->new CustomExcp("user not found..!"));
		DeliveryEntity delivery=deliveryDao.findByPaymentUser(user);
		DeliveryRespDto deliveryDto=new DeliveryRespDto();
		deliveryDto.setDescription(delivery.getDeliveryDesc());
		deliveryDto.setDeliveryDate(delivery.getDeliveryDate());
		deliveryDto.setPayment(mapper.map(delivery.getPayment(), PaymentRespDto.class));
		return deliveryDto;
	}

	@Override
	public ApiResponse issueDelivery(Long userId, DeliveryReqDto deliveryDto) {
		PaymentEntity payment=paymentDao.findById(userId).orElseThrow(()->new CustomExcp("Payment still pending yet..!"));
		DeliveryEntity delivery=mapper.map(deliveryDto, DeliveryEntity.class);
		delivery.setDeliveryDesc(deliveryDto.getDescription());
		delivery.setDeliveryDate(LocalDate.now());
		delivery.setDeliveryStatus(StatusEnum.DELIVERED);
		delivery.setPayment(payment);
		deliveryDao.save(delivery);	
		return new ApiResponse("Delivered SuccessFully..!");
	}
	
}
