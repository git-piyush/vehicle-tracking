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
		
		if(userAddress==null) {
			return false;
		}
		try {
			UserAddress address = userAddressRepository.save(userAddress);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public UserAddress findAddressById(Long id) {
		
		UserAddress address = userAddressRepository.findById(id).get();
		
		return address;
	}

}
