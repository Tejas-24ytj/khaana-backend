/**
 * 
 */
package com.capgemini.khaana.services;

import java.util.List;

import com.capgemini.khaana.entities.OrderItem;

/**
 * 
 */
public interface OrderItemService {

	List<OrderItem> getAllOrderItems();

	OrderItem getOrderItemById(Long id);

	OrderItem createOrderItem(OrderItem orderItem);

	OrderItem updateOrderItem(Long id, OrderItem orderItem);

	OrderItem patchOrderItem(Long id, OrderItem orderItem);

	void deleteOrderItem(Long id);

}
