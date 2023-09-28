package com.example.restapp.services;

import com.example.restapp.DTO.AppointmentDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.relations.Appointment;
import com.example.restapp.models.Status;
import com.example.restapp.repo.AppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {

     AppointmentDTO setAppointment(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> findAppointmentsByClientId(long clientId) ;

    List<AppointmentDTO> getAppointmentsByClientId(long id);
}
