package com.example.restapp.repo;

import com.example.restapp.models.relations.Appointment;
import com.example.restapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByClient(Client client);
}
