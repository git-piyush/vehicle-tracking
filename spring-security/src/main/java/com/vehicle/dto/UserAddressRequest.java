package com.vehicle.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserAddressRequest {
	
	@JsonIgnore
    private Long id;
	
	@NotNull(message = "City is a mandatory input.")
    private String city;

	@NotNull(message = "State is a mandatory input.")
    private String state;

	@NotNull(message = "Country is a mandatory input.")
    private String country;

	@NotNull(message = "Country is a mandatory input.")
	@Size(min = 6, max = 6, message = "Add a vaild Pin code")
    private Long pin;

    private String addType;
    
    @JsonIgnore
    private Date modifiedDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private String createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
