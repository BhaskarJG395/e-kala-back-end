package com.app.dto.request;

import java.time.LocalDate;

import com.app.entities.StatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryReqDto {
	
	private LocalDate deliveryDate;
	
	private String description;
	
	private  StatusEnum deliveryStatus;

}
