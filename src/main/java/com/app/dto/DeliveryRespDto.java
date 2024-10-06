package com.app.dto;

import java.time.LocalDate;

import com.app.entities.StatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryRespDto {
	
	private LocalDate deliveryDate;
	
	private String description;
	
	private  StatusEnum deliveryStatus;
	
	private PaymentRespDto payment;
}
