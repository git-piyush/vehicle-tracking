package com.vehicle.services;

import org.springframework.stereotype.Service;

import com.vehicle.entities.UserInfo;

@Service
public interface UserService {

	public UserInfo findById(Long userId);
	
	public UserInfo findByEmail(String email);
	
}
