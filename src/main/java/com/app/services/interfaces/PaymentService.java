package com.app.services.interfaces;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentRespDto;
import com.app.dto.request.PaymentReqDto;

public interface PaymentService {
	
	List<PaymentRespDto> getAllPaymentWithUsers(Long userId);
	
	ApiResponse makePayment(Long userId, Long orderId, PaymentReqDto paymentDto);
	
	List<PaymentRespDto> getAllPayments();
}
