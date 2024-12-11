package com.vehicle.services;

import com.vehicle.dto.SignupRequest;
import com.vehicle.entities.UserInfo;
import com.vehicle.repository.UserInfoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserInfoRepository userInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserInfo createCustomer(SignupRequest signupRequest) {
        //Check if customer already exist
        if (userInfoRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        UserInfo customer = new UserInfo();
        BeanUtils.copyProperties(signupRequest,customer);

        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);
        UserInfo createdCustomer = userInfoRepository.save(customer);
        customer.setId(createdCustomer.getId());
        return customer;
    }
}
