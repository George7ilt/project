package com.example.restapp.services;

import com.example.restapp.DTO.ClientDTO;
import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Client;
import com.example.restapp.repo.ClientRepo;
import com.example.restapp.util.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClientServiceimpl implements ClientService {
    @Autowired
    private  ClientRepo clientRepo;
    @Autowired
    private  Converter converter;

    public Client findByName(String name) {
        Optional<Client> optionalClient = clientRepo.findByName(name);
        return optionalClient.orElseThrow(() -> new ClientNotFoundException("Client with that name not found"));
    }

    public Client findById(long clientId) {
        Optional<Client> optionalClient = clientRepo.findById(clientId);
        return optionalClient.orElseThrow(() -> new ClientNotFoundException("Client with that id not found"));
    }

    public ClientDTO findClientDTOById(long clientId) {
        Optional<Client> optionalClient = clientRepo.findById(clientId);
        Client client = optionalClient.orElseThrow(() -> new ClientNotFoundException("Client with that id not found"));
        return converter.convertToClientDTO(client);

    }

    public void create(ClientDTO clientDTO) {
        clientRepo.save(converter.convertToClient(clientDTO));
    }

    public List<TattooDTO> getClientTattoos(long id) {
        return findById(id).getTattoos().stream().map(clientTattoo -> converter.convertToTattooDTO(clientTattoo.getTattoo())).toList();
    }

    public List<ClientDTO> findClientsWithMoreThanOneAppointment() {
        return clientRepo.findClientsWithMoreThanOneAppointment()
                .stream().map(converter::convertToClientDTO).toList();
    }
}
