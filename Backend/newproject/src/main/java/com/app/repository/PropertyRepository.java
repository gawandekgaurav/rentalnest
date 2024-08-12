package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Property;
import com.app.entity.Property.PropertyStatus;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findByLandlord_UserId(int landlordId);
    List<Property> findByStatus(PropertyStatus status);
}
