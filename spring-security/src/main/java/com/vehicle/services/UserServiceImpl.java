package com.vehicle.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.entities.UserInfo;
import com.vehicle.repository.UserInfoRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo findById(Long userId) {
		Optional<UserInfo> user = userInfoRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

}
