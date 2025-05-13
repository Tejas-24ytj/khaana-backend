/**
 * 
 */
package com.capgemini.khaana.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import com.capgemini.khaana.entities.FoodItem;

/**
 * 
 */
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

	List<FoodItem> findAllByRestaurantID(long restaurantID);

	@NativeQuery("SELECT * FROM food_item fi WHERE fi.restaurantid = ?1 ORDER BY fi.id DESC LIMIT 3")
	List<FoodItem> findRecentlyAddedItemByRestaurantID(Long restaurantID);

	@NativeQuery("Select fi.id as fooditem_id,fi.name as fooditem, count(*) as ordered from food_item fi, orders o, order_item oi, restaurant r Where oi.itemid = fi.id and oi.orderid = o.id and r.id=o.restaurantid and r.id = ?1 Group by fi.id order by ordered desc LIMIT 1;")
	List<Object[]> getTop1FoodItemByRestaurantID(Long restaurantID);

	@NativeQuery("Select fi.id as fooditem_id,fi.name as fooditem, count(*) as ordered from food_item fi, orders o, order_item oi, restaurant r Where oi.itemid = fi.id and oi.orderid = o.id and r.id=o.restaurantid and r.id = ?1 Group by fi.id order by ordered asc LIMIT 1;")
	List<Object[]> getBottom1FoodItemByRestaurantID(Long restaurantID);

	@NativeQuery("Select fi.id as fooditem_id,fi.name as fooditem, count(*) as ordered, r.name from food_item fi, orders o, order_item oi, restaurant r Where oi.itemid = fi.id and oi.orderid = o.id and r.id=o.restaurantid and fi.restaurantid = r.id Group by fi.id order by ordered desc LIMIT 1;")
	List<Object[]> getTop1FoodItemForAdmin();

	@NativeQuery("Select fi.id as fooditem_id,fi.name as fooditem, count(*) as ordered, r.name from food_item fi, orders o, order_item oi, restaurant r Where oi.itemid = fi.id and oi.orderid = o.id and r.id=o.restaurantid and fi.restaurantid = r.id Group by fi.id order by ordered asc LIMIT 1;")
	List<Object[]> getBottom1FoodItemForAdmin();

	@NativeQuery("SELECT fi.name, oi.quantity FROM food_item fi, order_item oi, orders o WHERE oi.itemid = fi.id AND oi.orderid = o.id AND o.restaurantid = ?1 AND o.order_date = ?2;")
	List<Object[]> getItemsSoldByRestaurantIDAndOnDate(Long restaurantID, LocalDate date);
}
