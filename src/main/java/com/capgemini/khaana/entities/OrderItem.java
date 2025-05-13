/**
 * 
 */
package com.capgemini.khaana.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * "orderitems": [ { "orderID": "o6t2p", "itemID": "f1e3b", "quantity": 1, "id":
 * "oi1xv" }]
 */
@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderID;
	private Long itemID;
	private Integer quantity;

	public OrderItem() {
	}

	public OrderItem(Long id, Long orderID, Long itemID, Integer quantity) {
		this.id = id;
		this.orderID = orderID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderID=" + orderID + ", itemID=" + itemID + ", quantity=" + quantity + "]";
	}

}
