package com.tunehub.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	public User findByEmail(String email);
}
