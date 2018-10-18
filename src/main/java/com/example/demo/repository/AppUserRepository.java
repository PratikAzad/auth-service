package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.AppUser;


public interface AppUserRepository extends CrudRepository<AppUser, String>{
	
	Optional<AppUser> findByUsername(String username);
}