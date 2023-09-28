package com.example.restapp.services;

import com.example.restapp.DTO.ClientDTO;
import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Client;
import com.example.restapp.repo.ClientRepo;
import com.example.restapp.util.exceptions.ClientNotFoundException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {

    Client findByName(String name);

    Client findById(long clientId);

    ClientDTO findClientDTOById(long clientId);

   void create(ClientDTO clientDTO);
    List<TattooDTO> getClientTattoos(long id);
    List<ClientDTO> findClientsWithMoreThanOneAppointment();
}
