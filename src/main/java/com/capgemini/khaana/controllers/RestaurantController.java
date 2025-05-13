/**
 * 
 */
package com.capgemini.khaana.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.khaana.entities.Restaurant;
import com.capgemini.khaana.services.RestaurantService;

/**
 * 
 */
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping
	ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getAllRestaurants());
	}

	@GetMapping("/owner/{currentOwnerId}")
	ResponseEntity<Restaurant> getCurrentRestaurantDetails(@PathVariable Long currentOwnerId) {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findByOwnerID(currentOwnerId));
	}

	@GetMapping("/{id}")
	ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantById(id));
	}

	// returns total number of restaurants
	@GetMapping("/count")
	ResponseEntity<Long> getTotalRestaurantsCount() {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getTotalRestaurantsCount());
	}

	@GetMapping("/sorted-by-orders")
	ResponseEntity<List<Object[]>> restaurantsBySortedOrderForAdmin() {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.restaurantsBySortedOrderForAdmin());
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<Restaurant> addRestaurant(@RequestParam String name, @RequestParam String location,
			@RequestParam String phone, @RequestParam Long ownerID, @RequestParam("resImage") MultipartFile resImage)
			throws IOException {
		Restaurant newRestaurant = restaurantService.createRestaurant(name, location, phone, ownerID, resImage);
		return ResponseEntity.status(HttpStatus.CREATED)
				.location(URI.create("/api/restaurants/" + newRestaurant.getId())).body(newRestaurant);
	}

	// To serve image if needed
	@GetMapping("/image/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
		// Point to the correct subdirectory
		Path filePath = Paths.get("uploads/restaurants", filename);

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

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestParam String name,
			@RequestParam String location, @RequestParam String phone, @RequestParam Long ownerID,
			@RequestParam(value = "resImage", required = false) MultipartFile resImage) throws IOException {

		Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, name, location, phone, ownerID, resImage);
		return ResponseEntity.ok(updatedRestaurant);
	}

	@PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<Restaurant> patchRestaurant(@PathVariable Long id, @RequestParam(required = false) String name,
			@RequestParam(required = false) String location, @RequestParam(required = false) String phone,
			@RequestParam(required = false) Long ownerID,
			@RequestParam(value = "resImage", required = false) MultipartFile resImage) throws IOException {

		Restaurant patchedRestaurant = restaurantService.patchRestaurant(id, name, location, phone, ownerID, resImage);
		return ResponseEntity.ok(patchedRestaurant);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/customers/details/{restaurantID}")
	public ResponseEntity<List<Object[]>> getCustomerDetailsByRestaurantID(@PathVariable Long restaurantID) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(restaurantService.getCustomerDetailsByRestaurantID(restaurantID));
	}

}
