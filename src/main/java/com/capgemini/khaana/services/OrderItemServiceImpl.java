/**
 * 
 */
package com.capgemini.khaana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.khaana.entities.OrderItem;
import com.capgemini.khaana.exceptions.OrderItemNotFoundException;
import com.capgemini.khaana.repositories.OrderItemRepository;

/**
 * 
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository repository;

	@Autowired
	public OrderItemServiceImpl(OrderItemRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public OrderItem getOrderItemById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id)
				.orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id:" + id));
	}

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return repository.save(orderItem);
	}

	@Override
	public OrderItem updateOrderItem(Long id, OrderItem newOrderItem) {
		// TODO Auto-generated method stub
		OrderItem oldOrderItem = repository.findById(id)
				.orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id:" + id));

		oldOrderItem.setOrderID(newOrderItem.getOrderID());
		oldOrderItem.setItemID(newOrderItem.getItemID());
		oldOrderItem.setQuantity(newOrderItem.getQuantity());
		return repository.save(oldOrderItem);
	}

	@Override
	public OrderItem patchOrderItem(Long id, OrderItem newOrderItem) {
		// TODO Auto-generated method stub
		OrderItem oldOrderItem = repository.findById(id)
				.orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id:" + id));

		if (newOrderItem.getOrderID() != null) {
			oldOrderItem.setOrderID(newOrderItem.getOrderID());
		}
		if (newOrderItem.getItemID() != null) {
			oldOrderItem.setItemID(newOrderItem.getItemID());
		}
		if (newOrderItem.getQuantity() != null) {
			oldOrderItem.setQuantity(newOrderItem.getQuantity());
		}
		return repository.save(oldOrderItem);
	}

	@Override
	public void deleteOrderItem(Long id) {
		// TODO Auto-generated method stub
		if (!repository.existsById(id)) {
			throw new OrderItemNotFoundException("OrderItem not found with id:" + id);
		}
		repository.deleteById(id);

	}

}
