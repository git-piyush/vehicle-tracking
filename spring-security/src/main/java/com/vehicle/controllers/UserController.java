package com.vehicle.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.dto.AddressResponse;
import com.vehicle.dto.UserAddressRequest;
import com.vehicle.dto.UserProfile;
import com.vehicle.entities.UserAddress;
import com.vehicle.entities.UserInfo;
import com.vehicle.exception.ResourceNotFoundException;
import com.vehicle.mapper.EntityToResponseMapper;
import com.vehicle.mapper.RequestToEntityMapper;
import com.vehicle.services.UserAddressService;
import com.vehicle.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAddressService userAddressService;
	
	@Autowired
	private EntityToResponseMapper entityToResponseMapper;
	
	@Autowired
	private RequestToEntityMapper requestToEntityMapper;
	
	@PostMapping("/address/{userId}")
	public ResponseEntity createUserAddress(@PathVariable Long userId,@RequestBody @Valid UserAddressRequest addressRequest, BindingResult result) {
		String message = null;
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
        }
        Exception e = new Exception();
        UserInfo userInfo = userService.findById(userId);
        UserAddress userAddress = new UserAddress();
        if(userInfo == null) {
        	return new ResponseEntity<String>("User Not Found Exception.", HttpStatus.BAD_REQUEST);
        }else {      	   	       	
        	userAddress = requestToEntityMapper.userAddressRequestToUserAddressEntity(addressRequest);
        	if(userInfo.getAddress()!=null) {
        		userAddress.setId(userInfo.getAddress().getId());
        		message = "Address has been update.";
        	}
        }
        userAddress.setUserInfo(userInfo);
        boolean addressCreated = userAddressService.saveUserAddress(userAddress);
        message = "Address created successfully.";
        if(!addressCreated) {
        	return new ResponseEntity<String>("Address Creation faild.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
		return new ResponseEntity<String>("Address Created.", HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@GetMapping("/address/{userId}")
	public ResponseEntity getUserAddress(@PathVariable Long userId) {
        Exception e = new Exception();
        UserInfo userInfo = userService.findById(userId);
        if(userInfo == null) {
        	return new ResponseEntity<String>("User Not Found.", HttpStatus.BAD_REQUEST);
        }
        UserAddress userAddress = userInfo.getAddress();
        AddressResponse response;
        if(userAddress == null) {
        	return new ResponseEntity<String>("User dont have addrerss details.", HttpStatus.BAD_REQUEST);
        }else {      	   	       	
        	response = entityToResponseMapper.userAddressEntityToUserAddressResponse(userInfo.getAddress());
        }
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	
	
	@GetMapping("/profile/{userId}")
	public ResponseEntity getUserProfile(@PathVariable Long userId) {
		List<Exception> eList = new ArrayList<Exception>();
        Exception e = new Exception();
        UserInfo userInfo = userService.findById(userId);
        if(userInfo == null) {
        	return new ResponseEntity<String>("User Not Found.", HttpStatus.BAD_REQUEST);
        }
        UserAddress userAddress = userInfo.getAddress();
        if(userAddress == null) {
        	return new ResponseEntity<String>("User dont have addrerss details.", HttpStatus.BAD_REQUEST);
        }else {      	   	       	
        	
        }
        UserProfile userProfile = entityToResponseMapper.userInfoEntityToUserProfile(userInfo);
        
        
        
        
        
		return new ResponseEntity<>(userProfile, HttpStatus.OK);		
	}
	
	
	

}
