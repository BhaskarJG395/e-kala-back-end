package com.app.services.interfaces;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.DeliveryRespDto;
import com.app.dto.request.DeliveryReqDto;

public interface DeliveryServices {
	List<DeliveryRespDto> getAllDelivery();
	
	DeliveryRespDto getDeliveryByUser(Long userId);
	
	ApiResponse issueDelivery(Long userId, DeliveryReqDto deliveryDto);
}
