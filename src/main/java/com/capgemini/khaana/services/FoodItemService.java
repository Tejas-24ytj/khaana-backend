/**
 * 
 */
package com.capgemini.khaana.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.FoodItem;

/**
 * 
 */
public interface FoodItemService {

	List<FoodItem> getAllFoodItems();

	FoodItem getFoodItemById(Long id);

//	FoodItem createFoodItem(FoodItem foodItem);

	FoodItem createFoodItem(String name, String category, Integer price, Long restaurantID, MultipartFile file)
			throws IOException;

	FoodItem updateFoodItem(Long id, FoodItem foodItem);

	FoodItem patchFoodItem(Long id, FoodItem foodItem);

	void deleteFoodItem(Long id);

	List<FoodItem> getFoodItemsForRestaurant(Long restaurantID);

	List<FoodItem> findRecentlyAddedItemByRestaurantID(Long restaurantID);

	List<Object[]> getTop1FoodItemByRestaurantID(Long restaurantID);

	List<Object[]> getBottom1FoodItemByRestaurantID(Long restaurantID);

	public List<Object[]> getBottom1FoodItemForAdmin();

	public List<Object[]> getTop1FoodItemForAdmin();

	List<Object[]> getItemsSoldByRestaurantIDAndOnDate(Long restaurantID, LocalDate date);

}
