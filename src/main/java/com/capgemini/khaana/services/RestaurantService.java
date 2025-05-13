/**
 * 
 */
package com.capgemini.khaana.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.Restaurant;

/**
 * 
 */

public interface RestaurantService {
	List<Restaurant> getAllRestaurants();

	Restaurant getRestaurantById(Long id);

	Restaurant createRestaurant(String name, String location, String phone, Long ownerID, MultipartFile file)
			throws IOException;

	Restaurant updateRestaurant(Long id, String name, String location, String phone, Long ownerID, MultipartFile file)
			throws IOException;

	Restaurant patchRestaurant(Long id, String name, String location, String phone, Long ownerID, MultipartFile file)
			throws IOException;

	void deleteRestaurant(Long id);

	Restaurant findByOwnerID(Long ownerID);

	List<Object[]> getCustomerDetailsByRestaurantID(Long restaurantID);

	Long getTotalRestaurantsCount();

	List<Object[]> restaurantsBySortedOrderForAdmin();

}
