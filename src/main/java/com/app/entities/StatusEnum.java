package com.app.entities;

public enum StatusEnum {
	ORDER_RECIVED(1),
	PAYMENT_RECIVED(2),
	DELIVERED(3),
	PENDING(4);
	
	private int statusId;
	
	private StatusEnum(int id) {
		this.statusId=id;
	}
	
	public int getStatusId() {
		return statusId;
	}

}
