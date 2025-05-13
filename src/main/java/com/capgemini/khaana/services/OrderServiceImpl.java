/**
 * 
 */
package com.capgemini.khaana.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.khaana.dtos.OrderDTO;
import com.capgemini.khaana.entities.Order;
import com.capgemini.khaana.exceptions.OrderNotFoundException;
import com.capgemini.khaana.repositories.OrderRepository;

/**
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	@Autowired
	public OrderServiceImpl(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Order> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id:" + id));
	}

	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return repository.save(order);
	}

	@Override
	public Order updateOrder(Long id, Order newOrder) {
		// TODO Auto-generated method stub
		Order oldOrder = repository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order not found with id:" + id));
		oldOrder.setUserID(newOrder.getUserID());
		oldOrder.setRestaurantID(newOrder.getRestaurantID());
		oldOrder.setOrderDate(newOrder.getOrderDate());
		oldOrder.setTotalAmount(newOrder.getTotalAmount());
		return repository.save(oldOrder);
	}

	@Override
	public Order patchOrder(Long id, Order newOrder) {
		// TODO Auto-generated method stub
		Order oldOrder = repository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
		if (newOrder.getUserID() != null) {
			oldOrder.setUserID(newOrder.getUserID());
		}
		if (newOrder.getRestaurantID() != null) {
			oldOrder.setRestaurantID(newOrder.getRestaurantID());
		}
		if (newOrder.getOrderDate() != null) {
			oldOrder.setOrderDate(newOrder.getOrderDate());
		}
		if (newOrder.getTotalAmount() != null) {
			oldOrder.setTotalAmount(newOrder.getTotalAmount());
		}
		return repository.save(oldOrder);

	}

	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		if (!repository.existsById(id)) {
			throw new OrderNotFoundException("Order not found with id: " + id);
		}
		repository.deleteById(id);
	}

	@Override
	public List<Order> findByRestaurantID(Long restaurantID) {
		// TODO Auto-generated method stub
		return repository.findByRestaurantID(restaurantID);
	}

//	@Override
//	public Long countByRestaurantID(Long restaurantID) {
//		// TODO Auto-generated method stub
//		return repository.countByRestaurantID(restaurantID);
//	}

	@Override
	public Long countByRestaurantIDAndOrderDate(Long restaurantID, LocalDate date) {
		if (date == null) {
			// All-time count
			return repository.countByRestaurantID(restaurantID);
		} else {
			// Count on specific date
			return repository.countByRestaurantIDAndOrderDate(restaurantID, date);
		}
	}

//	@Override
//	public Long countByRestaurantIDAndfindByorderDate(Long restaurantID) {
//		// TODO Auto-generated method stub
//		LocalDate todayDate = LocalDate.now();
//		return repository.countByRestaurantIDAndfindByorderDate(restaurantID, todayDate);
//	}

//	@Override
//	public Long calcTotalRevenueByRestaurantID(Long restaurantID) {
//		// TODO Auto-generated method stub
//		return repository.calcTotalRevenueByRestaurantID(restaurantID);
//	}

	@Override
	public Long calcTotalRevenueByRestaurantIDAndDate(Long restaurantID, LocalDate date) {
		if (date == null) {
			// All-time total revenue
			return repository.calcTotalRevenueByRestaurantID(restaurantID);
		} else {
			// Total revenue on a specific date
			return repository.calcTotalRevenueByRestaurantIDAndOrderDate(restaurantID, date);
		}
	}

//	@Override
//	public Long calcTotalRevenueByRestaurantIDToday(Long restaurantID) {
//		// TODO Auto-generated method stub
//		LocalDate todayDate = LocalDate.now();
//		return repository.calcTotalRevenueByRestaurantIDToday(restaurantID, todayDate);
//	}

	@Override
	public List<Object[]> dataForOrdersPerWeekChart(Long restaurantID) {
		// TODO Auto-generated method stub
		return repository.dataForOrdersPerWeekChart(restaurantID);
	}

	@Override
	public List<Object[]> dataForOrdersPerWeekChartForAdmin() {
		// TODO Auto-generated method stub
		return repository.dataForOrdersPerWeekChartForAdmin();
	}

	@Override
	public List<Object[]> dataForRevenuePerWeekChart(Long restaurantID) {
		// TODO Auto-generated method stub
		return repository.dataForRevenuePerWeekChart(restaurantID);
	}

	@Override
	public List<Object[]> dataForRevenuePerWeekChartForAdmin() {
		// TODO Auto-generated method stub
		return repository.dataForRevenuePerWeekChartForAdmin();
	}

	@Override
	public List<Object[]> dataForRevenuePerMonthChart(Long restaurantID) {
		// TODO Auto-generated method stub
		return repository.dataForRevenuePerMonthChart(restaurantID);
	}

	@Override
	public List<Object[]> dataForRevenuePerMonthChartForAdmin() {
		// TODO Auto-generated method stub
		return repository.dataForRevenuePerMonthChartForAdmin();
	}

//	public List<OrderDTO> getViewOrdersDetailsForRestaurant(Long restaurantId) {
//		List<Object[]> rows = repository.getViewOrdersDetailsForRestaurant(restaurantId);
//		Map<Long, OrderDTO> orderMap = new LinkedHashMap<>(); // preserves insertion order
//
//		for (Object[] row : rows) {
//			Long orderID = ((Number) row[0]).longValue();
//			String customerName = (String) row[1];
//			String customerLocation = (String) row[2];
//			LocalDate orderDate = ((java.sql.Date) row[3]).toLocalDate();
//			String itemName = (String) row[4];
//			Integer quantity = ((Number) row[5]).intValue();
//			Long totalAmount = ((Number) row[6]).longValue();
//
//			OrderDTO dto = orderMap.get(orderID);
//
//			if (dto == null) {
//				dto = new OrderDTO();
//				dto.setOrderID(orderID);
//				dto.setCustomerName(customerName);
//				dto.setCustomerLocation(customerLocation);
//				dto.setOrderDate(orderDate);
//				dto.setTotalAmount(totalAmount);
//				orderMap.put(orderID, dto);
//			}
//
//			// Add item to list
//			dto.getItems().add(String.format("%s (x%d)", itemName, quantity));
//		}
//		System.out.println(orderMap.values());
//
//		return new ArrayList<>(orderMap.values());
//	}

	public List<OrderDTO> getViewOrdersDetailsForRestaurant(Long restaurantId, LocalDate date) {
		List<Object[]> rows;
		if (date == null) {
			rows = repository.getViewOrdersDetailsByRestaurantID(restaurantId);
		} else {
			rows = repository.getViewOrdersDetailsByRestaurantIDAndOrderDate(restaurantId, date);
		}
		Map<Long, OrderDTO> orderMap = new LinkedHashMap<>(); // preserves insertion order

		for (Object[] row : rows) {
			Long orderID = ((Number) row[0]).longValue();
			String customerName = (String) row[1];
			String customerLocation = (String) row[2];
			LocalDate orderDate = ((java.sql.Date) row[3]).toLocalDate();
			String itemName = (String) row[4];
			Integer quantity = ((Number) row[5]).intValue();
			Long totalAmount = ((Number) row[6]).longValue();

			OrderDTO dto = orderMap.get(orderID);

			if (dto == null) {
				dto = new OrderDTO();
				dto.setOrderID(orderID);
				dto.setCustomerName(customerName);
				dto.setCustomerLocation(customerLocation);
				dto.setOrderDate(orderDate);
				dto.setTotalAmount(totalAmount);
				orderMap.put(orderID, dto);
			}

			// Add item to list
			dto.getItems().add(String.format("%s (x%d)", itemName, quantity));
		}
		System.out.println(orderMap.values());

		return new ArrayList<>(orderMap.values());
	}

	@Override
	public List<Object[]> getRevenueByCategory(Long restaurantID) {
		return repository.getRevenueByCategory(restaurantID);
	}

	@Override
	public List<Object[]> getRevenueByCategoryForAdmin() {
		return repository.getRevenueByCategoryForAdmin();
	}

	@Override
	public List<OrderDTO> getMyOrdersDetailsForCustomer(Long userID) {
		// TODO Auto-generated method stub
		List<Object[]> rows = repository.getMyOrdersDetailsForCustomer(userID);
		Map<Long, OrderDTO> orderMap = new LinkedHashMap<>(); // preserves insertion order

		for (Object[] row : rows) {
			Long orderID = ((Number) row[0]).longValue();
//			String customerName = (String) row[1];
//			String customerLocation = (String) row[2];
			LocalDate orderDate = ((java.sql.Date) row[1]).toLocalDate();
			String itemName = (String) row[2];
			Integer itemPrice = ((Number) row[3]).intValue();
			Integer quantity = ((Number) row[4]).intValue();
			Long totalAmount = ((Number) row[5]).longValue();
			String restaurantID = (String) row[6];
			OrderDTO dto = orderMap.get(orderID);

			if (dto == null) {
				dto = new OrderDTO();
				dto.setOrderID(orderID);
//				dto.setCustomerName(customerName);
//				dto.setCustomerLocation(customerLocation);
				dto.setOrderDate(orderDate);
				dto.setItemPrice(itemPrice);
				dto.setTotalAmount(totalAmount);
				dto.setRestaurantID(restaurantID);
				orderMap.put(orderID, dto);
			}

			// Add item to list
			dto.getItems().add(String.format("%s (x%d)", itemName, quantity));
		}
//		System.out.println(orderMap.values());

		return new ArrayList<>(orderMap.values());
	}

	public List<OrderDTO> getRecentOrdersForAdmin() {
		List<Object[]> rows = repository.getRecentOrdersForAdmin();
		Map<Long, OrderDTO> orderMap = new LinkedHashMap<>(); // preserves insertion order

		for (Object[] row : rows) {
			Long orderID = ((Number) row[0]).longValue();
			String customerName = (String) row[1];
			String customerLocation = (String) row[2];
			LocalDate orderDate = ((java.sql.Date) row[3]).toLocalDate();
			String itemName = (String) row[4];
			Integer quantity = ((Number) row[5]).intValue();
			Long totalAmount = ((Number) row[6]).longValue();
			String restaurantID = (String) row[7];
			OrderDTO dto = orderMap.get(orderID);

			if (dto == null) {
				dto = new OrderDTO();
				dto.setOrderID(orderID);
				dto.setCustomerName(customerName);
				dto.setCustomerLocation(customerLocation);
				dto.setOrderDate(orderDate);
				dto.setTotalAmount(totalAmount);
				dto.setRestaurantID(restaurantID);
				orderMap.put(orderID, dto);
			}

			// Add item to list
			dto.getItems().add(String.format("%s (x%d)", itemName, quantity));
		}
		System.out.println(orderMap.values());

		return new ArrayList<>(orderMap.values());
	}

}
