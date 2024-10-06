package com.app.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressReqDto {
	
	private String fullAddress;
	
	private String landmark;
	
	private String city;
	
	private String country;
	
	private int pincode;
}
