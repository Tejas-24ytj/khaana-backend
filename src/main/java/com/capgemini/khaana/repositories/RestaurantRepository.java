/**
 * 
 */
package com.capgemini.khaana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import com.capgemini.khaana.entities.Restaurant;

/**
 * 
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	Restaurant findByOwnerID(Long ownerID);

	Long countBy();

	@NativeQuery("SELECT u.id as customer_id ,u.name AS customer_name, COUNT(o.id) AS total_orders, SUM(o.total_amount) AS total_spend, MAX(o.order_date) AS last_order_date FROM user u, orders o, restaurant r Where u.id = o.userid AND r.id = o.restaurantid AND r.id =?1 GROUP BY u.id, u.name ORDER BY total_spend DESC Limit 3;")
	List<Object[]> getCustomerDetailsByRestaurantID(Long restaurantID);

	@NativeQuery("Select r.id as restaurant_id,r.name as restaurant, count(*) as ordered from restaurant r, orders o Where r.id=o.restaurantid Group by r.id order by ordered DESC ;")
	List<Object[]> restaurantsBySortedOrderForAdmin();
}
