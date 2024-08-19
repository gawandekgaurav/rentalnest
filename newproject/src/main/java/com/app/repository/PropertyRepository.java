package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Property;
import com.app.entity.Property.HouseType;
import com.app.entity.Property.PropertyStatus;
import com.app.entity.User;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
	List<Property> findByLandlord_UserId(int landlordId);

	List<Property> findByStatus(PropertyStatus status);

	List<Property> findByHouseType(HouseType houseType); // Add method to find by houseType

	List<Property> findByCityArea(String cityArea); // Add method to find by cityArea

	
}
