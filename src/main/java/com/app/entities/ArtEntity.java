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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="art")
public class ArtEntity extends BaseEntity {
	@Column(length = 25, nullable = false)
	private String artName;
	@Column(length = 50, nullable = false)
	private String description;
	@Column(length = 20)
	private String artType;
	private int quantity;
	private double price;
	private String artImagePath;	
	
	@OneToMany(mappedBy = "art",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<OrderEntity>order=new ArrayList<OrderEntity>();
	
	
	public void addOrder(OrderEntity order) {
		this.order.add(order);
		order.setArt(this);
	}
	
	public void removeOrder(OrderEntity order) {
		this.order.remove(order);
		order.setArt(null);
	}
}
