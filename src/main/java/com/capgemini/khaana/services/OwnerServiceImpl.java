/**
 * 
 */
package com.capgemini.khaana.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.khaana.repositories.OrderItemRepository;

/**
 * 
 */
public class OwnerServiceImpl implements OwnerService {

	OrderItemRepository orderItemRepository;

	@Autowired
	public OwnerServiceImpl(OrderItemRepository orderItemRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
	}

//	@Override
//	public ItemDTO dataForTopItem() {
//		ItemDTO itemDTO = new ItemDTO();
//
//		itemDTO.setItemID(orderItemRepository.findAll());
//		itemDTO.setQuantity();
//		return null;
//	}
}
