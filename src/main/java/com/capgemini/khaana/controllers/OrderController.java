/**
 * 
 */
package com.capgemini.khaana.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.khaana.dtos.OrderDTO;
import com.capgemini.khaana.entities.Order;
import com.capgemini.khaana.services.OrderService;

/**
 * 
 */
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService service;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}

	@GetMapping
	ResponseEntity<List<Order>> getAllOrders() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrders());
	}

	// returns orders for the given restaurant id
	@GetMapping("/restaurant/{restaurantID}")
	ResponseEntity<List<Order>> getOrdersByRestaurant(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByRestaurantID(restaurantID));
	}

	/**
	 * // returns orders count for the given restaurant id OLD INDIVIDUAL
	 * CONTROLLERS @GetMapping("/restaurant/count/{restaurantID}")
	 * ResponseEntity<Long> getOrdersCountByRestaurant(@PathVariable Long
	 * restaurantID) { return
	 * ResponseEntity.status(HttpStatus.OK).body(service.countByRestaurantID(restaurantID));
	 * }
	 * 
	 * // returns todays orders count for the given restaurant
	 * id @GetMapping("/restaurant/count/today/{restaurantID}") ResponseEntity<Long>
	 * countByRestaurantIDAndfindByorderDate(@PathVariable Long restaurantID) {
	 * return
	 * ResponseEntity.status(HttpStatus.OK).body(service.countByRestaurantIDAndfindByorderDate(restaurantID));
	 * }
	 */

	@GetMapping("/restaurant/count/{restaurantID}")
	public ResponseEntity<Long> getOrdersCount(@PathVariable Long restaurantID,
			@RequestParam(value = "date", required = false) String date) {

		Long count;

		if (date == null) {
			// 1. All-time count
			count = service.countByRestaurantIDAndOrderDate(restaurantID, null);
		} else if (date.equalsIgnoreCase("today")) {
			// 2. Today's count GET /restaurant/count/5?date=today
			count = service.countByRestaurantIDAndOrderDate(restaurantID, LocalDate.now());
		} else {
			try {
				LocalDate parsedDate = LocalDate.parse(date);
				// 3. Specific date count
				count = service.countByRestaurantIDAndOrderDate(restaurantID, parsedDate);
			} catch (DateTimeParseException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0L); // Or return a meaningful error response
			}
		}

		return ResponseEntity.ok(count);
	}

	/**
	 * // returns total revenue amount for the given restaurant
	 * id @GetMapping("/restaurant/revenue/{restaurantID}") ResponseEntity<Long>
	 * calcTotalRevenueByRestaurantID(@PathVariable Long restaurantID) { return
	 * ResponseEntity.status(HttpStatus.OK).body(service.calcTotalRevenueByRestaurantID(restaurantID));
	 * }
	 * 
	 * // returns today's revenue amount for the given restaurant
	 * id @GetMapping("/restaurant/revenue/today/{restaurantID}")
	 * ResponseEntity<Long> calcTotalRevenueByRestaurantIDToday(@PathVariable Long
	 * restaurantID) { return
	 * ResponseEntity.status(HttpStatus.OK).body(service.calcTotalRevenueByRestaurantIDToday(restaurantID));
	 * }
	 */

	@GetMapping("/restaurant/revenue/{restaurantID}")
	public ResponseEntity<Long> calcTotalRevenue(@PathVariable Long restaurantID,
			@RequestParam(value = "date", required = false) String date) {

		Long revenue;

		if (date == null) {
			// 1. All-time revenue
			revenue = service.calcTotalRevenueByRestaurantIDAndDate(restaurantID, null);
		} else if (date.equalsIgnoreCase("today")) {
			// 2. Today's revenue GET /restaurant/revenue/5?date=today
			revenue = service.calcTotalRevenueByRestaurantIDAndDate(restaurantID, LocalDate.now());
		} else {
			try {
				LocalDate parsedDate = LocalDate.parse(date);
				// 3. Specific date revenue
				revenue = service.calcTotalRevenueByRestaurantIDAndDate(restaurantID, parsedDate);
			} catch (DateTimeParseException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0L); // Or return a meaningful error response
			}
		}

		return ResponseEntity.ok(revenue);
	}

//	// returns object for dataForOrdersPerWeekChart  for the given restaurant id
	@GetMapping("/restaurant/charts/orders-per-week/{restaurantId}")
	ResponseEntity<List<Object[]>> dataForOrdersPerWeekChart(@PathVariable Long restaurantId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForOrdersPerWeekChart(restaurantId));
	}

//	// returns object for dataForOrdersPerWeekChart for admin
	@GetMapping("/restaurant/charts/orders-per-week")
	ResponseEntity<List<Object[]>> dataForOrdersPerWeekChartForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForOrdersPerWeekChartForAdmin());
	}

//	// returns object for dataForRevenuePerWeekChart  for the given restaurant id
	@GetMapping("/restaurant/charts/revenue-per-week/{restaurantId}")
	ResponseEntity<List<Object[]>> dataForRevenuePerWeekChart(@PathVariable Long restaurantId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForRevenuePerWeekChart(restaurantId));
	}

//	// returns object for dataForRevenuePerWeekChart  for admin
	@GetMapping("/restaurant/charts/revenue-per-week")
	ResponseEntity<List<Object[]>> dataForRevenuePerWeekChartForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForRevenuePerWeekChartForAdmin());
	}

//	// returns object for dataForRevenuePerMonthChart for the given restaurant id
	@GetMapping("/restaurant/charts/revenue-per-month/{restaurantId}")
	ResponseEntity<List<Object[]>> dataForRevenuePerMonthChart(@PathVariable Long restaurantId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForRevenuePerMonthChart(restaurantId));
	}

//	// returns object for dataForRevenuePerMonthChart for admin
	@GetMapping("/restaurant/charts/revenue-per-month")
	ResponseEntity<List<Object[]>> dataForRevenuePerMonthChartForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(service.dataForRevenuePerMonthChartForAdmin());
	}

//	// returns object for getRevenueByCategory for the given restaurant id
	@GetMapping("/restaurant/charts/revenue-by-category/{restaurantID}")
	public ResponseEntity<List<Object[]>> getRevenueByCategory(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getRevenueByCategory(restaurantID));
	}

	@GetMapping("/restaurant/charts/revenue-by-category")
	public ResponseEntity<List<Object[]>> getRevenueByCategoryForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getRevenueByCategoryForAdmin());
	}

////	// returns object for getViewOrdersDetailsForRestaurant  for the given restaurant id
//	@GetMapping("/detail/restaurant/{restaurantID}")
//	ResponseEntity<List<OrderDTO>> getViewOrdersDetailsForRestaurant(@PathVariable Long restaurantID) {
//		return ResponseEntity.status(HttpStatus.OK).body(service.getViewOrdersDetailsForRestaurant(restaurantID));
//	}

//	// returns object for getViewOrdersDetailsForRestaurant  for the given restaurant id
	@GetMapping("/detail/restaurant/{restaurantID}")
	ResponseEntity<List<OrderDTO>> getViewOrdersDetailsForRestaurant(@PathVariable Long restaurantID,
			@RequestParam(value = "date", required = false) String date) {

		if (date == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getViewOrdersDetailsForRestaurant(restaurantID, null));
		} else if (date.equalsIgnoreCase("today")) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getViewOrdersDetailsForRestaurant(restaurantID, LocalDate.now()));

		} else {
			try {
				LocalDate parsedDate = LocalDate.parse(date);
				return ResponseEntity.status(HttpStatus.OK)
						.body(service.getViewOrdersDetailsForRestaurant(restaurantID, parsedDate));
			} catch (DateTimeParseException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
			}
		}
	}

//	@GetMapping("/recents/{restaurantID}")
//	ResponseEntity<List<OrderDTO>> getRecentOrdersForOwner(@PathVariable Long restaurantID) {
//		return ResponseEntity.status(HttpStatus.OK).body(service.getRecentOrdersForOwner(restaurantID));
//	}

	@GetMapping("/recents")
	ResponseEntity<List<OrderDTO>> getRecentOrdersForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getRecentOrdersForAdmin());
	}

	// For cutomer MyOrders page
	@GetMapping("/detail/customer/{userID}")
	ResponseEntity<List<OrderDTO>> getMyOrdersDetailsForCustomer(@PathVariable Long userID) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getMyOrdersDetailsForCustomer(userID));
	}

	@GetMapping("/{id}")
	ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getOrderById(id));
	}

	@PostMapping
	ResponseEntity<Order> createOrder(@RequestBody Order order) {
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/orders/" + order.getId()))
				.body(service.createOrder(order));
	}

	@PutMapping("/{id}")
	ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateOrder(id, order));
	}

	@PatchMapping("/{id}")
	ResponseEntity<Order> patchOrder(@PathVariable Long id, @RequestBody Order order) {
		return ResponseEntity.status(HttpStatus.OK).body(service.patchOrder(id, order));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
		service.deleteOrder(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
