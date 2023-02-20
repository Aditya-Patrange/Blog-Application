package com.blogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	
}
