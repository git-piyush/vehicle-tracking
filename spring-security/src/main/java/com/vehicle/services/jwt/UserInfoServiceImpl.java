package com.vehicle.services.jwt;

import com.vehicle.entities.UserInfo;
import com.vehicle.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserInfoServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Write logic to fetch customer from DB
    	UserInfo userInfo = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        return new User(userInfo.getEmail(), userInfo.getPassword(), Collections.emptyList());
    }
}
