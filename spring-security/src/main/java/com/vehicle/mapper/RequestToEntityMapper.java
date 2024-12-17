package com.vehicle.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.vehicle.dto.AddressResponse;
import com.vehicle.dto.UserAddressRequest;
import com.vehicle.entities.UserAddress;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface RequestToEntityMapper {

	UserAddress userAddressRequestToUserAddressEntity(UserAddressRequest address);

}
