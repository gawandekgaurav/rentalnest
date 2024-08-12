package com.app.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Application;
import com.app.entity.Property;
import com.app.entity.User;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByProperty(Property property);
    List<Application> findByTenant(User tenant);
}
