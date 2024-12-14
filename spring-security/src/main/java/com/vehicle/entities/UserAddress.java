package com.vehicle.entities;

import java.util.Date;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="TBL_ADDRESS")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String state;

    private String country;

    private Long pin;

    private String addType;
    
    private Date modifiedDate;

    private String modifiedBy;

    private Date createdDate;

    private String createdBy;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private UserInfo user;
    
    @PreUpdate
    @PrePersist
    public void updateTimeStamps()
    {
    	String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
             userName = authentication.getName();
        	}    
        this.modifiedDate = new Date();
        this.modifiedBy = userName;
        if(this.createdDate == null) {
            this.createdDate = new Date();
            this.createdBy = userName;
        }
    }

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

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
	
}