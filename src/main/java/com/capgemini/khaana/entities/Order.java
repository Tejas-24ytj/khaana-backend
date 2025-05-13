/**
 * 
 */
package com.capgemini.khaana.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * "Order": [ { "userID": "c8x2f", "restaurantID": "r2k9d", "orderdate":
 * "2025-04-17T14:30:00Z", "totalamount": 154, "id": "o6t2p" }]
 */
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userID;

	private Long restaurantID;

	private LocalDate orderDate;

	private Long totalAmount;

	public Order() {
	}

	public Order(Long id, Long userID, Long restaurantID, LocalDate orderDate, Long totalAmount) {
		this.id = id;
		this.userID = userID;
		this.restaurantID = restaurantID;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(Long restaurantID) {
		this.restaurantID = restaurantID;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userID=" + userID + ", restaurantID=" + restaurantID + ", orderDate=" + orderDate
				+ ", totalAmount=" + totalAmount + "]";
	}

}
