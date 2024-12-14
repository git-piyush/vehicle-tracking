package com.vehicle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.entities.UserAddress;
import com.vehicle.repository.UserAddressRepository;

@Service
public class UserAddressServiceImpl implements UserAddressService {
	
	@Autowired
	private UserAddressRepository userAddressRepository;

	@Override
	public boolean saveUserAddress(UserAddress userAddress) {
		
		return false;
	}

}
