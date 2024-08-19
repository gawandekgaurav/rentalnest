package com.app.dto;

import java.sql.Date;

import com.app.entity.Property.HouseType;
import com.app.entity.Property.PropertyStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDTO {
	private int propertyID;
	private int landlordID;
	private String address;
	private String description;
	private double rent;
	private PropertyStatus status;
	private Date availableFrom;
	private Date availableTo;
	private HouseType houseType; // Include houseType field
	private String cityArea;
	private String imageData;

}
