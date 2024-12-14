package com.vehicle.services;

import org.springframework.stereotype.Service;

import com.vehicle.entities.UserAddress;

@Service
public interface UserAddressService {

	public boolean saveUserAddress(UserAddress userAddress);
	
}
