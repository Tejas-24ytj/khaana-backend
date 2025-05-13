/**
* 
*/
package com.capgemini.khaana.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * "name": "Tasty Bites", "location": "Downtown", "phone": "9876543210",
 * "ownerID": "u1a9b", "id": "r2k9d"
 */
@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String location;
	private String phone;
	private Long ownerID;
	private String resImage;

	public Restaurant(Long id, String name, String location, String phone, Long ownerID, String resImage) {

		this.id = id;
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.ownerID = ownerID;
		this.resImage = resImage;

	}

	public Restaurant() {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	public String getResImage() {
		return resImage;
	}

	public void setResImage(String resImage) {
		this.resImage = resImage;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", location=" + location + ", phone=" + phone + ", ownerID="
				+ ownerID + ", resImage=" + resImage + "]";
	}

}
