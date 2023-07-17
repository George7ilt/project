package com.example.restapp.controllers;

import com.example.restapp.DTO.ClientDTO;
import com.example.restapp.services.AppointmentService;
import com.example.restapp.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final AppointmentService appointmentService;

    @PostMapping
    //добавить клиента
    public ResponseEntity<?> create(@RequestBody @Valid ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.ok("clietn created");
    }

    //посмотреть клиента
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable long id) {
        return ResponseEntity.ok(clientService.findClientDTOById(id));
    }

    //посмотреть татухи клиента
    @GetMapping("/{id}/tattoos")
    public ResponseEntity<?> getClientTattoos(@PathVariable long id) {
        return ResponseEntity.ok(clientService.getClientTattoos(id));
    }

    //    посмотреть записи клиента
    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> getAppointments(@PathVariable long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByClientId(id));
    }

    //    Получить список клиентов, которые имеют более одной активной записи на татуировку.
    @GetMapping("/manyAppointments")
    public ResponseEntity<?> getManyAppointmentsClients() {
        return ResponseEntity.ok(clientService.findClientsWithMoreThanOneAppointment());
    }
}
