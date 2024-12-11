package com.vehicle.services;

import com.vehicle.dto.SignupRequest;
import com.vehicle.entities.UserInfo;

public interface AuthService {
	UserInfo createCustomer(SignupRequest signupRequest);
}
