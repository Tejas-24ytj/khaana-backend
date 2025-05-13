/**
 * 
 */
package com.capgemini.khaana.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.khaana.entities.User;
import com.capgemini.khaana.exceptions.UserNotFoundException;
import com.capgemini.khaana.repositories.UserRepository;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public User updateUser(Long id, User newUser) {
		// TODO Auto-generated method stub
		User oldUser = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setPassword(newUser.getPassword());
		oldUser.setLocation(newUser.getLocation());
		oldUser.setPhone(newUser.getPhone());
		oldUser.setUserType(newUser.getUserType());
		return repository.save(oldUser);
	}

	@Override
	public User patchUser(Long id, User newUser) {
		User oldUser = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		if (newUser.getName() != null) {
			oldUser.setName(newUser.getName());
		}
		if (newUser.getEmail() != null) {
			oldUser.setEmail(newUser.getEmail());
		}
		if (newUser.getPassword() != null) {
			oldUser.setPassword(newUser.getPassword());
		}
		if (newUser.getLocation() != null) {
			oldUser.setLocation(newUser.getLocation());
		}
		if (newUser.getPhone() != null) {
			oldUser.setPhone(newUser.getPhone());
		}
		if (newUser.getUserType() != null) {
			oldUser.setUserType(newUser.getUserType());
		}
		return repository.save(oldUser);
	}

	@Override
	public void deleteUser(Long id) {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		repository.deleteById(id);

	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.existsByEmail(email);
	}

}
