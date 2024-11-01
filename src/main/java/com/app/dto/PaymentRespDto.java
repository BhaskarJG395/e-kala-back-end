package com.app.dto;

import java.time.LocalDate;

import com.app.entities.StatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRespDto {
	
	private double amount;
	
	private LocalDate paymentDate;
	
	private String paymentDescription;
	
	private StatusEnum paymentStatus;
	
	private UserDto user;
	
	private OrderRespDto order;
	
}
