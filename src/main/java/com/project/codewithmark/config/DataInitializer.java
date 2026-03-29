package com.project.codewithmark.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.codewithmark.model.entity.ServiceType;
import com.project.codewithmark.model.enums.ServiceTypeEnum;
import com.project.codewithmark.repository.ServiceTypeRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initServiceTypes(ServiceTypeRepository serviceTypeRepository) {
        return args -> {
            for (ServiceTypeEnum type : ServiceTypeEnum.values()) {
                if (!serviceTypeRepository.existsByType(type)) {
                    ServiceType serviceType = new ServiceType();
                    serviceType.setType(type);
                    serviceTypeRepository.save(serviceType);
                }
            }
        };
    }
}