package com.codex.modal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.codex.domain.OrderStatus;
import com.codex.domain.OrderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// one order can have many user
	@ManyToOne
	private User user;
	
	@Column(nullable=false) // order is required can't be null
	private OrderType orderType;
	
	@Column(nullable=false) // order is required can't be null
	private BigDecimal price;
	
	private LocalDateTime timestamp=LocalDateTime.now();
	
	@Column(nullable=false) // order is required can't be null
	private OrderStatus status;
	
	// oner order one item
	@OneToOne(mappedBy = "order",cascade=CascadeType.ALL) // reflect changes in OrderType
	private OrderItem orderItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
