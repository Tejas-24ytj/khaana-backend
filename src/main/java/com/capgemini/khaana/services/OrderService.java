/**
 * 
 */
package com.capgemini.khaana.services;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.khaana.dtos.OrderDTO;
import com.capgemini.khaana.entities.Order;

/**
 * 
 */
public interface OrderService {

	List<Order> getAllOrders();

	Order getOrderById(Long id);

	Order createOrder(Order order);

	Order updateOrder(Long id, Order order);

	Order patchOrder(Long id, Order order);

	void deleteOrder(Long id);

	List<Order> findByRestaurantID(Long restaurantID);

//	Long countByRestaurantID(Long restaurantID);

	Long countByRestaurantIDAndOrderDate(Long restaurantID, LocalDate date);

//	Long countByRestaurantIDAndfindByorderDate(Long restaurantID);

//	Long calcTotalRevenueByRestaurantID(Long restaurantID);

	Long calcTotalRevenueByRestaurantIDAndDate(Long restaurantID, LocalDate date);

//	Long calcTotalRevenueByRestaurantIDToday(Long restaurantID);

	List<Object[]> dataForOrdersPerWeekChart(Long restaurantID);

	List<Object[]> dataForOrdersPerWeekChartForAdmin();

	List<Object[]> dataForRevenuePerWeekChart(Long restaurantID);

	List<Object[]> dataForRevenuePerWeekChartForAdmin();

	List<Object[]> dataForRevenuePerMonthChart(Long restaurantID);

	List<Object[]> dataForRevenuePerMonthChartForAdmin();

//	List<OrderDTO> getViewOrdersDetailsForRestaurant(Long restaurantID);

	List<OrderDTO> getViewOrdersDetailsForRestaurant(Long restaurantID, LocalDate date);

	List<Object[]> getRevenueByCategory(Long restaurantID);

	List<Object[]> getRevenueByCategoryForAdmin();

	List<OrderDTO> getMyOrdersDetailsForCustomer(Long userID);

	List<OrderDTO> getRecentOrdersForAdmin();

//	List<OrderDTO> getRecentOrdersForOwner(Long restaurantId);

}
