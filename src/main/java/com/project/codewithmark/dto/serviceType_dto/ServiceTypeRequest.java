package com.project.codewithmark.dto.serviceType_dto;

import com.project.codewithmark.model.enums.ServiceTypeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTypeRequest {

    @NotNull(message = "Service type is required")
    private ServiceTypeEnum type;

    private String description;
    private double duration;
    private double price;
    private String code;
}
