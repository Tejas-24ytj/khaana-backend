
package com.capgemini.khaana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.khaana.entities.User;

/**
 * Repository for User Entity
 */

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);

}
