/**
 * 
 */
package com.capgemini.khaana.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * "fooditems": [ { "name": "Cheeseburger", "category": "Fast Food", "price":
 * "79", "restaurantID": "r2k9d", "id": "f1e3b" }]
 */
@Entity
public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String category;
	private Integer price;
	private Long restaurantID;
	private String foodImage;

	public FoodItem() {
	}

	public FoodItem(Long id, String name, String category, Integer price, Long restaurantID, String foodImage) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.restaurantID = restaurantID;
		this.foodImage = foodImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(Long restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", restaurantID=" + restaurantID + ", foodImage=" + foodImage + "]";
	}

}
