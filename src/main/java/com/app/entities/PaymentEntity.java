package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")
public class PaymentEntity extends BaseEntity {
	private double amount;
	private LocalDate paymentDate;
	private String paymentDescription;
	@Enumerated(EnumType.STRING)
	private StatusEnum paymentStatus;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserEntity user;
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name="order_id")
	private OrderEntity order;
	
	@Override
	public String toString() {
		return "Payment [amount=" + amount + ", paymentDate=" + paymentDate + ", paymentDescription="
				+ paymentDescription + ", paymentStatus=" + paymentStatus + ", user=" + user + ", order=" + order + "]";
	}
	
}
