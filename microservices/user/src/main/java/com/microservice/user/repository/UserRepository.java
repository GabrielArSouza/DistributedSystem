package com.microservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.user.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	@Query("SELECT u FROM user u WHERE u.email=?1 AND u.password=?2")
	User loginUser (String email, String password);
	
}
