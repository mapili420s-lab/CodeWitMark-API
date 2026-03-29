package com.project.codewithmark.dto.serviceType_dto;

import com.project.codewithmark.model.enums.ServiceTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeResponse {

    private Long id;
    private ServiceTypeEnum type;

    private String description;
    private double duration;
    private double price;
    private String code;
}
