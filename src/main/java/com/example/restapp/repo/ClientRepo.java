package com.example.restapp.repo;

import com.example.restapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByName(String name);

    @Query(value = """
            SELECT c , COUNT(a.id) AS active_appointments
            FROM Client c
            INNER JOIN Appointment a ON c.id = a.client.id
            WHERE a.status = 'ACTIVE'
            GROUP BY c.id
            HAVING COUNT(a.id) > 1
            """)
    List<Client> findClientsWithMoreThanOneAppointment();
}
