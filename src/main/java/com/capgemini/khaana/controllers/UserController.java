/**
 * 
 */
package com.capgemini.khaana.controllers;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.khaana.entities.User;
import com.capgemini.khaana.services.UserService;

/**
 * 
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
	}

//checks if user already exists
	@GetMapping("/exists/{email}")
	public ResponseEntity<Boolean> doesUserExist(@PathVariable String email) {
		return ResponseEntity.status(HttpStatus.OK).body(service.existsByEmail(email));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = service.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users/" + newUser.getId()))
				.body(newUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.status(HttpStatus.OK).body(service.patchUser(id, user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> patchUser(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
