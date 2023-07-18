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
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final ClientService clientService;

    private final Converter converter;


    public AppointmentDTO setAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = converter.convertToAppointment(appointmentDTO);
        appointment.setStatus(Status.ACTIVE);
        appointment.setDateTime(LocalDateTime.now());

        appointmentRepo.save(appointment);
        return converter.convertToAppointmentDTO(appointment);
    }

    public List<AppointmentDTO> findAppointmentsByClientId(long clientId) {
        return appointmentRepo.findAllByClient(clientService.findById(clientId)).
                stream().map(converter::convertToAppointmentDTO).toList();
    }

    public List<AppointmentDTO> getAppointmentsByClientId(long id) {
        return appointmentRepo.findAllByClient(clientService.findById(id))
                .stream().map(converter::convertToAppointmentDTO).toList();
    }
}
