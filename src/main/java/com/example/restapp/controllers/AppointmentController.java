package com.example.restapp.controllers;

import com.example.restapp.DTO.AppointmentDTO;
import com.example.restapp.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    //    записаться на тату к мастеру в студию ++
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> setAppointment(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.setAppointment(appointmentDTO));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getAppointmentsByClientId(@PathVariable long clientId) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByClientId(clientId));
    }
}
