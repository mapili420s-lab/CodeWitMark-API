package com.project.codewithmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.codewithmark.dto.mapper.UserMapper;
import com.project.codewithmark.dto.serviceType_dto.ServiceTypeResponse;
import com.project.codewithmark.model.entity.ServiceType;
import com.project.codewithmark.model.enums.ServiceTypeEnum;
import com.project.codewithmark.repository.ServiceTypeRepository;

@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private UserMapper userMapper;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<ServiceTypeResponse> getAllServiceTypes() {

        return userMapper.toServiceTypeResponseList(serviceTypeRepository.findAll());
    }

    public ServiceTypeResponse getServiceTypeById(Long id) {

        ServiceType serviceType = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Service type not found with id: " + id));

        return userMapper.toServiceTypeResponse(serviceType);
    }

    public ServiceTypeResponse getServiceTypeByType(String type) {

        ServiceTypeEnum enumType = ServiceTypeEnum.valueOf(type);

        ServiceType serviceType = serviceTypeRepository.findByType(enumType)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Service type not found with type: " + enumType));

        return userMapper.toServiceTypeResponse(serviceType);
    }

}