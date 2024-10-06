package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ArtResDto {
	private String artName;

	private String description;
	
	private String artType;
	
	private int quantity;
	
	private double price;
}
