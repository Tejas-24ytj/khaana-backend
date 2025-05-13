/**
 * 
 */
package com.capgemini.khaana.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.Restaurant;
import com.capgemini.khaana.exceptions.RestaurantNotFoundException;
import com.capgemini.khaana.repositories.RestaurantRepository;

/**
 * 
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id:" + id));
	}

	@Override
	public Restaurant createRestaurant(String name, String location, String phone, Long ownerID, MultipartFile file)
			throws IOException {
		// Define the directory to save uploaded images
		String UPLOAD_DIR = "uploads/restaurants/";

		// Create the directory if it doesn't exist
		Files.createDirectories(Paths.get(UPLOAD_DIR));

		// Generate a unique file name to avoid overwriting
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR, fileName);

		// Save the file to disk
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		Restaurant restaurant = new Restaurant();
		// Set the file name (or path) to the restaurant entity
		restaurant.setResImage(fileName);
		restaurant.setLocation(location);
		restaurant.setName(name);
		restaurant.setOwnerID(ownerID);
		restaurant.setPhone(phone);
		// Save the restaurant to the database
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long id, String name, String location, String phone, Long ownerID,
			MultipartFile file) throws IOException {

		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));

		// If new image is provided, replace the old one
		if (file != null && !file.isEmpty()) {
			String UPLOAD_DIR = "uploads/restaurants/";
			Files.createDirectories(Paths.get(UPLOAD_DIR));
			String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR, fileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			restaurant.setResImage(fileName);
		}

		restaurant.setName(name);
		restaurant.setLocation(location);
		restaurant.setPhone(phone);
		restaurant.setOwnerID(ownerID);

		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant patchRestaurant(Long id, String name, String location, String phone, Long ownerID,
			MultipartFile file) throws IOException {

		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));

		if (name != null)
			restaurant.setName(name);
		if (location != null)
			restaurant.setLocation(location);
		if (phone != null)
			restaurant.setPhone(phone);
		if (ownerID != null)
			restaurant.setOwnerID(ownerID);

		if (file != null && !file.isEmpty()) {
			String UPLOAD_DIR = "uploads/restaurants/";
			Files.createDirectories(Paths.get(UPLOAD_DIR));
			String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR, fileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			restaurant.setResImage(fileName);
		}

		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long id) {
		// TODO Auto-generated method stub
		if (!restaurantRepository.existsById(id)) {
			throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
		}
		restaurantRepository.deleteById(id);
	}

	@Override
	public Restaurant findByOwnerID(Long ownerID) {
		// TODO Auto-generated method stub
		return restaurantRepository.findByOwnerID(ownerID);
	}

	@Override
	public List<Object[]> getCustomerDetailsByRestaurantID(Long restaurantID) {
		// TODO Auto-generated method stub
		return restaurantRepository.getCustomerDetailsByRestaurantID(restaurantID);
	}

	@Override
	public Long getTotalRestaurantsCount() {
		// TODO Auto-generated method stub
		return restaurantRepository.countBy();
	}

	@Override
	public List<Object[]> restaurantsBySortedOrderForAdmin() {
		// TODO Auto-generated method stub
		return restaurantRepository.restaurantsBySortedOrderForAdmin();
	}
}
