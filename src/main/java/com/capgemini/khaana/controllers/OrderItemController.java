/**
 * 
 */
package com.capgemini.khaana.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.khaana.entities.OrderItem;
import com.capgemini.khaana.services.OrderItemService;

/**
 * 
 */
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemController {

	private final OrderItemService service;

	@Autowired
	public OrderItemController(OrderItemService service) {
		super();
		this.service = service;
	}

	@GetMapping
	ResponseEntity<List<OrderItem>> getAllOrderItems() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrderItems());
	}

	@GetMapping("/{id}")
	ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getOrderItemById(id));
	}

	@PostMapping
	ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) {
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/orderitems/" + orderItem.getId()))
				.body(service.createOrderItem(orderItem));
	}

	@PutMapping("/{id}")
	ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateOrderItem(id, orderItem));
	}

	@PatchMapping("/{id}")
	ResponseEntity<OrderItem> patchOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
		return ResponseEntity.status(HttpStatus.OK).body(service.patchOrderItem(id, orderItem));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<OrderItem> deleteOrderItem(@PathVariable Long id) {
		service.deleteOrderItem(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
