package com.vehicle.controllers;

import com.vehicle.dto.LoginRequest;
import com.vehicle.dto.LoginResponse;
import com.vehicle.entities.UserInfo;
import com.vehicle.services.UserService;
import com.vehicle.services.jwt.UserInfoServiceImpl;
import com.vehicle.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final UserInfoServiceImpl userService;

    private final JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService2;


    @Autowired
    public LoginController(AuthenticationManager authenticationManager, UserInfoServiceImpl userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        String message = null;
    	try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
        	message = "Incorrect email or password.";
        	return new LoginResponse(null, null, null, message);
            //throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated.");
            return null;
        }
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        UserInfo userInfo = userService2.findByEmail(userDetails.getUsername());
        return new LoginResponse(jwt, userDetails.getUsername(), userInfo.getId() ,message);
    }

}
