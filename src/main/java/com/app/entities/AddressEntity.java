package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class AddressEntity extends BaseEntity{
	@Column(length = 100)
	private String fullAddress;
	
	@Column(length = 20)
	private String landmark;
	
	@Column(length = 20)
	private String city;
	
	@Column(name="country",length = 20)
	private String country;
	
	private int pincode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@MapsId
	private UserEntity user;
	
	public void setUser(UserEntity user) {
		this.user=user;
	}

	@Override
	public String toString() {
		return "Address [fullAddress=" + fullAddress + ", landmark=" + landmark + ", city=" + city + ", countery="
				+ country + ", pincode=" + pincode + ", user=" + user + "]";
	}
}
