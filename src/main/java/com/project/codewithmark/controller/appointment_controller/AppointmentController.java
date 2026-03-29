package com.project.codewithmark.controller.appointment_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.codewithmark.dto.appointment_dto.AppointmentRequest;
import com.project.codewithmark.dto.appointment_dto.AppointmentResponse;
import com.project.codewithmark.model.entity.User;
import com.project.codewithmark.repository.UserRepository;
import com.project.codewithmark.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserRepository userRepository;

    public AppointmentController(AppointmentService appointmentService, UserRepository userRepository) {
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping("appointment/create")
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Valid @RequestBody AppointmentRequest appointmentRequest, @AuthenticationPrincipal User user) {

        if (user == null) {
            user = userRepository.findById(1L) // Or findByEmail("test@test.com")
                    .orElseThrow(() -> new RuntimeException("Database is empty! Create a user first."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createAppointment(appointmentRequest,
                        user));
    }
}
