package com.vehicle.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.vehicle.dto.AddressResponse;
import com.vehicle.dto.UserProfile;
import com.vehicle.entities.UserAddress;
import com.vehicle.entities.UserInfo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface EntityToResponseMapper {

	AddressResponse userAddressEntityToUserAddressResponse(UserAddress address);
	
	@Mapping(target="userName", source = "name")
	@Mapping(target="code", source = "countryCode")
	UserProfile userInfoEntityToUserProfile(UserInfo userInfo);
	
}
