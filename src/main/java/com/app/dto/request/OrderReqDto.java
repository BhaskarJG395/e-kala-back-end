package com.app.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OrderReqDto {
//		 private Long id;
//		 private Double amount;
//		 private LocalDate deliveryDate;
//		 private LocalDate orderDate;
//		 private String orderStatus;
//		 private Long artId;
//		 private Long userId;
		 
		    private LocalDate orderDate;
		    private LocalDate deliveryDate;
		    private Double amount;
		    private String orderStatus; // Or use StatusEnum if you have it in DTO

		    private Long userId;
		    private Long artId;

}
