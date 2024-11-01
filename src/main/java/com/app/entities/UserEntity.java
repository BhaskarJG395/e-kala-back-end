package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {
	
	@Column(length = 30 , nullable = false)
	private String firstName;
	@Column(length = 30 , nullable=false)
	private String lastName;
	private String role;
	@Column(length = 12)
	private String mobileNo;
	@Column(length = 30, unique=true, nullable = false)
	private String email;
	@Column(length = 15,nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PaymentEntity> payment=new ArrayList<>();
	
	@OneToMany(mappedBy = "user", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<OrderEntity>order=new ArrayList<OrderEntity>();
	
	@OneToMany(mappedBy = "user", orphanRemoval = true,cascade = CascadeType.ALL)
	private List<AddressEntity>address=new ArrayList<AddressEntity>();
	
//	Helper methods
	public void addPayment(PaymentEntity payment) {
		this.payment.add(payment);
		payment.setUser(this);
	}
	
	public void removePayment(PaymentEntity payment) {
		this.payment.remove(payment);
		payment.setUser(null);
	}
	
	public void addOrder(OrderEntity order) {
		this.order.add(order);
		order.setUser(this);
	}
	
	public void removeOrder(OrderEntity order) {
		this.order.remove(order);
		order.setUser(null);
	}
	public void addAddress(AddressEntity address) {
		this.address.add(address);
		address.setUser(this);
	}
	
	public void removeAddress(AddressEntity address) {
		this.address.remove(address);
		address.setUser(null);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", password=" + password + ", payment=" + payment + ", order=" + order + "]";
	}
}
