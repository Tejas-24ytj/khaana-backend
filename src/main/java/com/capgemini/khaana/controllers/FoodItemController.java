/**
 * 
 */
package com.capgemini.khaana.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.FoodItem;
import com.capgemini.khaana.services.FoodItemService;

/**
 * 
 */
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemController {

	private final FoodItemService foodItemService;

	@Autowired
	public FoodItemController(FoodItemService foodItemService) {
		super();
		this.foodItemService = foodItemService;
	}

	@GetMapping
	ResponseEntity<List<FoodItem>> getAllFoodItems() {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getAllFoodItems());
	}

	@GetMapping("/restaurant/{restaurantID}")
	ResponseEntity<List<FoodItem>> getFoodItemsForRestaurant(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getFoodItemsForRestaurant(restaurantID));
	}

	@GetMapping("/{id}")
	ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getFoodItemById(id));
	}

//	@PostMapping
//	ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
//		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/fooditems/" + foodItem.getId()))
//				.body(foodItemService.createFoodItem(foodItem));
//	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<FoodItem> addFoodItem(@RequestParam String name, @RequestParam String category,
			@RequestParam Integer price, @RequestParam Long restaurantID,
			@RequestParam("foodImage") MultipartFile foodImage) throws IOException {

		FoodItem newFoodItem = foodItemService.createFoodItem(name, category, price, restaurantID, foodImage);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/fooditems/" + newFoodItem.getId()))
				.body(newFoodItem);
	}

	//
	@GetMapping("/image/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
		// Point to the correct subdirectory
		Path filePath = Paths.get("uploads/fooditems", filename);

		// Check if file exists
		if (!Files.exists(filePath)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Create resource
		Resource resource = new UrlResource(filePath.toUri());

		// Determine content type
		String contentType = Files.probeContentType(filePath);
		if (contentType == null) {
			contentType = "application/octet-stream"; // default fallback
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

	@PutMapping("/{id}")
	ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.updateFoodItem(id, foodItem));
	}

	@PatchMapping("/{id}")
	ResponseEntity<FoodItem> patchFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.patchFoodItem(id, foodItem));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<FoodItem> deleteFoodItem(@PathVariable Long id) {
		foodItemService.deleteFoodItem(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/recent/{restaurantID}")
	public ResponseEntity<List<FoodItem>> getMostRecentFoodItem(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(foodItemService.findRecentlyAddedItemByRestaurantID(restaurantID));
	}

	@GetMapping("/best/{restaurantID}")
	public ResponseEntity<List<Object[]>> getTop1FoodItemByRestaurantID(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getTop1FoodItemByRestaurantID(restaurantID));
	}

	@GetMapping("/least/{restaurantID}")
	public ResponseEntity<List<Object[]>> getBottom1FoodItemByRestaurantID(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(foodItemService.getBottom1FoodItemByRestaurantID(restaurantID));
	}

	@GetMapping("/best")
	public ResponseEntity<List<Object[]>> getTop1FoodItemForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getTop1FoodItemForAdmin());
	}

	@GetMapping("/least")
	public ResponseEntity<List<Object[]>> getBottom1FoodItemForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(foodItemService.getBottom1FoodItemForAdmin());
	}

	@GetMapping("/sold/{restaurantID}")
	public ResponseEntity<List<Object[]>> getItemsSoldByRestaurantIDAndOnDate(@PathVariable Long restaurantID,
			@RequestParam LocalDate date) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(foodItemService.getItemsSoldByRestaurantIDAndOnDate(restaurantID, date));
	}

}
