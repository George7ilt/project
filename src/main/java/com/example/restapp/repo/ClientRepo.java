package com.example.restapp.repo;

import com.example.restapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByName(String name);

    @Query(nativeQuery = true, value = """
            SELECT client.id, client.name, COUNT(Appointment.id) AS active_appointments
            FROM client
            INNER JOIN appointment ON client.id = appointment.client_id
            WHERE appointment.status = 'ACTIVE'
            GROUP BY client.id
            HAVING COUNT(appointment.id) > 1
            """)
    List<Client> findClientsWithMoreThanOneAppointment();
}
