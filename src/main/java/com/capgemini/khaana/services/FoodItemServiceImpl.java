/**
 * 
 */
package com.capgemini.khaana.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.FoodItem;
import com.capgemini.khaana.exceptions.FoodItemNotFoundException;
import com.capgemini.khaana.repositories.FoodItemRepository;

/**
 * 
 */
@Service
public class FoodItemServiceImpl implements FoodItemService {

	private final FoodItemRepository foodItemRepository;

	@Autowired
	public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
		this.foodItemRepository = foodItemRepository;
	}

	@Override
	public List<FoodItem> getAllFoodItems() {

		return foodItemRepository.findAll();
	}

	@Override
	public FoodItem getFoodItemById(Long id) {
		// TODO Auto-generated method stub
		return foodItemRepository.findById(id)
				.orElseThrow(() -> new FoodItemNotFoundException("FoodItem not found with id:" + id));
	}

	@Override
	public FoodItem createFoodItem(String name, String category, Integer price, Long restaurantID, MultipartFile file)
			throws IOException {
		String UPLOAD_DIR = "uploads/fooditems/";

		// Create the directory if it doesn't exist
		Files.createDirectories(Paths.get(UPLOAD_DIR));

		// Generate a unique file name to avoid overwriting
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR, fileName);

		// Save the file to disk
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		FoodItem foodItem = new FoodItem();
		// Set the file name (or path) to the restaurant entity
		foodItem.setFoodImage(fileName);
		foodItem.setCategory(category);
		foodItem.setName(name);
		foodItem.setPrice(price);
		foodItem.setRestaurantID(restaurantID);
		// Save the restaurant to the database
		return foodItemRepository.save(foodItem);
	}

	@Override
	public FoodItem updateFoodItem(Long id, FoodItem newFoodItem) {
		// TODO Auto-generated method stub
		FoodItem oldFoodItem = foodItemRepository.findById(id)
				.orElseThrow(() -> new FoodItemNotFoundException("FoodItem not found with id:" + id));

		oldFoodItem.setName(newFoodItem.getName());
		oldFoodItem.setCategory(newFoodItem.getCategory());
		oldFoodItem.setPrice(newFoodItem.getPrice());
		oldFoodItem.setRestaurantID(newFoodItem.getRestaurantID());
		return foodItemRepository.save(oldFoodItem);
	}

	@Override
	public FoodItem patchFoodItem(Long id, FoodItem newFoodItem) {
		// TODO Auto-generated method stub
		FoodItem oldFoodItem = foodItemRepository.findById(id)
				.orElseThrow(() -> new FoodItemNotFoundException("FoodItem not found with id:" + id));

		if (newFoodItem.getName() != null) {
			oldFoodItem.setName(newFoodItem.getName());
		}
		if (newFoodItem.getCategory() != null) {
			oldFoodItem.setCategory(newFoodItem.getCategory());
		}
		if (newFoodItem.getPrice() != null) {
			oldFoodItem.setPrice(newFoodItem.getPrice());
		}
		if (newFoodItem.getRestaurantID() != null) {
			oldFoodItem.setRestaurantID(newFoodItem.getRestaurantID());
		}
		return foodItemRepository.save(oldFoodItem);
	}

	@Override
	public void deleteFoodItem(Long id) {
		// TODO Auto-generated method stub
		if (!foodItemRepository.existsById(id)) {
			throw new FoodItemNotFoundException("FoodItem not found with id:" + id);
		}
		foodItemRepository.deleteById(id);

	}

	@Override
	public List<FoodItem> getFoodItemsForRestaurant(Long restaurantID) {
		// TODO Auto-generated method stub
		return foodItemRepository.findAllByRestaurantID(restaurantID);
	}

	@Override
	public List<FoodItem> findRecentlyAddedItemByRestaurantID(Long restaurantID) {
		// TODO Auto-generated method stub
		return foodItemRepository.findRecentlyAddedItemByRestaurantID(restaurantID);
	}

	@Override
	public List<Object[]> getBottom1FoodItemByRestaurantID(Long restaurantID) {
		// TODO Auto-generated method stub
		return foodItemRepository.getBottom1FoodItemByRestaurantID(restaurantID);
	}

	@Override
	public List<Object[]> getTop1FoodItemByRestaurantID(Long restaurantID) {
		// TODO Auto-generated method stub
		return foodItemRepository.getTop1FoodItemByRestaurantID(restaurantID);
	}

	@Override
	public List<Object[]> getBottom1FoodItemForAdmin() {
		// TODO Auto-generated method stub
		return foodItemRepository.getBottom1FoodItemForAdmin();
	}

	@Override
	public List<Object[]> getTop1FoodItemForAdmin() {
		// TODO Auto-generated method stub
		return foodItemRepository.getTop1FoodItemForAdmin();
	}

	@Override
	public List<Object[]> getItemsSoldByRestaurantIDAndOnDate(Long restaurantID, LocalDate date) {
		// TODO Auto-generated method stub
		return foodItemRepository.getItemsSoldByRestaurantIDAndOnDate(restaurantID, date);
	}
}
