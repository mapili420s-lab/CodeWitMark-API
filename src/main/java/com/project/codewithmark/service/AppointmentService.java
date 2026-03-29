package com.project.codewithmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.codewithmark.dto.appointment_dto.AppointmentRequest;
import com.project.codewithmark.dto.appointment_dto.AppointmentResponse;
import com.project.codewithmark.dto.mapper.UserMapper;
import com.project.codewithmark.model.entity.Appointment;
import com.project.codewithmark.model.entity.ServiceType;
import com.project.codewithmark.model.entity.Therapist;
import com.project.codewithmark.model.entity.User;
import com.project.codewithmark.model.enums.AppointmentStatus;
import com.project.codewithmark.repository.AppointmentRepository;
import com.project.codewithmark.repository.ServiceTypeRepository;
import com.project.codewithmark.repository.TherapistRepository;
import com.project.codewithmark.repository.UserRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final TherapistRepository therapistRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, TherapistRepository therapistRepository,
            ServiceTypeRepository serviceTypeRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.therapistRepository = therapistRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.userRepository = userRepository;
    }

    // Return all appointments
    public List<AppointmentResponse> getAllAppointments() {
        return userMapper.toAppointmentResponseList(appointmentRepository.findAll());
    }

    // Create an appointments
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest, User userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Therapist therapist = therapistRepository.findById(appointmentRequest.getTherapistId())
                .orElseThrow(() -> new RuntimeException("Therapist not found"));

        ServiceType serviceType = serviceTypeRepository.findById(appointmentRequest.getServiceType())
                .orElseThrow(() -> new RuntimeException("Service Type not found"));

        Appointment appointment = userMapper.toAppoinmentEntity(appointmentRequest);

        appointment.setTherapist(therapist);
        appointment.setUser(user);
        appointment.setServiceType(serviceType);
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

        return userMapper.toAppointmentResponse(appointmentRepository.save(appointment));
    }
}