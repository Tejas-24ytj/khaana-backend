/**
 * 
 */
package com.capgemini.khaana.services;

import java.util.List;

import com.capgemini.khaana.entities.User;

/**
 * 
 */
public interface UserService {

	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, User user);

	User patchUser(Long id, User user);

	void deleteUser(Long id);

	boolean existsByEmail(String email);

}
