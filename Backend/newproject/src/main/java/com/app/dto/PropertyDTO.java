package com.app.dto;

import java.time.LocalDate;
import java.util.List;

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
    private PropertyStatus status; // Use PropertyStatus enum
    private LocalDate availableFrom;
    private LocalDate availableTo;
    private List<String> imageURLs; // List of image URLs
}
