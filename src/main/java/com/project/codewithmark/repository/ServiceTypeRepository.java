package com.project.codewithmark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.codewithmark.model.entity.ServiceType;
import com.project.codewithmark.model.enums.ServiceTypeEnum;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    Optional<ServiceType> findByType(ServiceTypeEnum serviceType);

    boolean existsByType(ServiceTypeEnum type);
}
