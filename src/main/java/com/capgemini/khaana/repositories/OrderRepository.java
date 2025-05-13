/**
 * 
 */
package com.capgemini.khaana.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.khaana.entities.Order;

/**
 * 
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByRestaurantID(Long restaurantID);

	Long countByRestaurantID(Long restaurantID);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.restaurantID = ?1 AND o.orderDate = ?2 ")
	Long countByRestaurantIDAndOrderDate(Long restaurantID, LocalDate orderDate);

	@Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.restaurantID = ?1")
	Long calcTotalRevenueByRestaurantID(Long restaurantID);

	@Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.restaurantID = ?1 AND o.orderDate = ?2 ")
	Long calcTotalRevenueByRestaurantIDAndOrderDate(Long restaurantID, LocalDate orderDate);

	@NativeQuery("select WEEK(order_date) as Weeks, COUNT(*) AS Orders from orders o WHERE o.restaurantid=?1 group by Weeks order by Weeks;")
	List<Object[]> dataForOrdersPerWeekChart(Long restaurantID);

	@NativeQuery("select WEEK(order_date) as Weeks, COUNT(*) AS Orders from orders o group by Weeks order by Weeks;")
	List<Object[]> dataForOrdersPerWeekChartForAdmin();

	@NativeQuery("select WEEK(order_date) as Weeks, SUM(o.total_amount) AS Orders from orders o WHERE o.restaurantid=?1 group by Weeks order by Weeks;")
	List<Object[]> dataForRevenuePerWeekChart(Long restaurantID);

	@NativeQuery("select WEEK(order_date) as Weeks, SUM(o.total_amount) AS Orders from orders o group by Weeks order by Weeks;")
	List<Object[]> dataForRevenuePerWeekChartForAdmin();

	@NativeQuery("select MONTH(order_date) as Months, SUM(o.total_amount) AS Orders from orders o WHERE o.restaurantid=?1 group by Months order by Months;")
	List<Object[]> dataForRevenuePerMonthChart(Long restaurantID);

	@NativeQuery("select MONTH(order_date) as Months, SUM(o.total_amount) AS Orders from orders o group by Months order by Months;")
	List<Object[]> dataForRevenuePerMonthChartForAdmin();

//	@NativeQuery("select MONTH(order_date) as Weeks, AVG(o.total_amount) AS Orders from orders o WHERE o.restaurantid=?1 group by Weeks order by Weeks;")
//	List<Object[]> dataForAvgRevenuePerMonthChart(Long restaurantID);

	@NativeQuery("SELECT o.id, u.name, u.location, o.order_date, fi.name, oi.quantity, o.total_amount FROM orders o, order_item oi, user u, food_item fi, restaurant r WHERE o.userid = u.id AND o.id = oi.orderid AND oi.itemid = fi.id AND o.restaurantid = r.id AND r.id = ?1")
	List<Object[]> getViewOrdersDetailsForRestaurant(Long restaurantID);

	@NativeQuery("SELECT o.id, u.name, u.location, o.order_date, fi.name, oi.quantity, o.total_amount FROM orders o, order_item oi, user u, food_item fi, restaurant r WHERE o.userid = u.id AND o.id = oi.orderid AND oi.itemid = fi.id AND o.restaurantid = r.id AND r.id = ?1")
	List<Object[]> getViewOrdersDetailsByRestaurantID(Long restaurantID);

	@NativeQuery("SELECT o.id, u.name, u.location, o.order_date, fi.name, oi.quantity, o.total_amount FROM orders o, order_item oi, user u, food_item fi, restaurant r WHERE o.userid = u.id AND o.id = oi.orderid AND oi.itemid = fi.id AND o.restaurantid = r.id AND r.id = ?1 AND o.order_date = ?2")
	List<Object[]> getViewOrdersDetailsByRestaurantIDAndOrderDate(Long restaurantID, LocalDate orderDate);

	// work in progress above

	@NativeQuery("SELECT fi.category, SUM(fi.price * oi.quantity) AS revenue FROM order_item oi, food_item fi, orders o WHERE oi.itemid = fi.id AND oi.orderid = o.id AND fi.restaurantid = ?1 GROUP BY fi.category")
	List<Object[]> getRevenueByCategory(Long restaurantID);

	@NativeQuery("SELECT fi.category, SUM(fi.price * oi.quantity) AS revenue FROM order_item oi, food_item fi, orders o WHERE oi.itemid = fi.id AND oi.orderid = o.id GROUP BY fi.category")
	List<Object[]> getRevenueByCategoryForAdmin();

	@NativeQuery("SELECT o.id, order_date, fi.name,fi.price, oi.quantity, total_amount, r.name FROM orders o, order_item oi, user u, food_item fi, restaurant r WHERE o.userid = u.id AND o.id = oi.orderid AND oi.itemid = fi.id AND o.restaurantid = r.id AND u.id = ?1")
	List<Object[]> getMyOrdersDetailsForCustomer(Long userID);

	@NativeQuery("SELECT o.id, u.name, u.location, o.order_date, fi.name, oi.quantity, o.total_amount, r.name FROM orders o, order_item oi, user u, food_item fi, restaurant r WHERE o.userid = u.id AND o.id = oi.orderid AND oi.itemid = fi.id AND o.restaurantid = r.id ORDER BY o.id DESC limit 4")
	List<Object[]> getRecentOrdersForAdmin();

}
