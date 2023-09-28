package com.example.restapp.repo;

import com.example.restapp.models.relations.Appointment;
import com.example.restapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByClient(Client client);
}
