package com.project.codewithmark.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.codewithmark.model.entity.Appointment;
import com.project.codewithmark.model.entity.Therapist;
import com.project.codewithmark.model.entity.User;
import com.project.codewithmark.model.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByTherapistAndAppointmentStatusIn(Therapist therapist, List<AppointmentStatus> statuses);

    boolean existsByTherapistAndDate(
            Therapist therapist,
            LocalDate appointmentDate);

    boolean existsByUserAndDate(User user, LocalDate date);
}
