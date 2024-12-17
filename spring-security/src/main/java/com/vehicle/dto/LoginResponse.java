package com.vehicle.dto;

public record LoginResponse(String jwt, String user,Long userId, String message) {
	
	
}
