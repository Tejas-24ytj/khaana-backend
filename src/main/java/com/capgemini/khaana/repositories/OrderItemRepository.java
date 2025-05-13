/**
 * 
 */
package com.capgemini.khaana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import com.capgemini.khaana.entities.OrderItem;

/**
 * 
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@NativeQuery("SELECT itemID, quantity from OrderItem;")
	List<Object[]> dataForTopItem();

}
