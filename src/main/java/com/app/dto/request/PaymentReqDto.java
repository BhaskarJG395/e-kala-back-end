package com.app.dto.request;

import java.time.LocalDate;

import com.app.entities.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentReqDto {
	
	private double amount;
	
	private LocalDate paymentDate;
	
	private String paymentDescription;
	@JsonIgnore
	private StatusEnum paymentStatus;
}
