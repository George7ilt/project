package com.example.restapp.DTO.utils;

import com.example.restapp.DTO.*;
import com.example.restapp.models.*;
import com.example.restapp.models.relations.Appointment;
import com.example.restapp.services.ClientService;
import com.example.restapp.services.MasterService;
import com.example.restapp.services.StudioService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    private final ModelMapper modelMapper = new ModelMapper();
    private final ClientService clientService;
    private final MasterService masterService;
    private final StudioService studioService;

    public Converter(@Lazy ClientService clientService,
                     @Lazy MasterService masterService,
                     @Lazy StudioService studioService) {
        this.clientService = clientService;
        this.masterService = masterService;
        this.studioService = studioService;
    }

    public Appointment convertToAppointment(AppointmentDTO dto) {
        Appointment appointment = modelMapper.map(dto, Appointment.class);
        appointment.setClient(clientService.findByName(dto.getClientName()));
        appointment.setMaster(masterService.findByName(dto.getMasterName()));
        return appointment;
    }

    public AppointmentDTO convertToAppointmentDTO(Appointment appointment) {
        AppointmentDTO dto = modelMapper.map(appointment, AppointmentDTO.class);
        dto.setMasterName(appointment.getMaster().getName());
        dto.setClientName(appointment.getClient().getName());
        StudioDTO studioDTO = convertToStudioDTO(appointment.getMaster().getStudio());
        dto.setStudio(studioDTO);
        return dto;
    }

    public StudioDTO convertToStudioDTO(Studio studio) {
        return modelMapper.map(studio, StudioDTO.class);
    }

    public Studio convertToStudio(StudioDTO studioDTO) {
        Studio studio = modelMapper.map(studioDTO, Studio.class);
        studio.setMasters(studioService.findStudioById(studio.getId()).getMasters());
        return studio;
    }


    public Master convertToMaster(MasterDTO masterDTO) {
        Master master = modelMapper.map(masterDTO, Master.class);
        master.setStudio(studioService.findStudioById(masterDTO.getStudioId()));
        return master;
    }

    public MasterDTO convertToMasterDTO(Master master) {
        MasterDTO dto = modelMapper.map(master, MasterDTO.class);
        dto.setStudio(convertToStudioDTO(master.getStudio()));
        return dto;
    }


    public TattooDTO convertToTattooDTO(Tattoo tattoo) {
        return modelMapper.map(tattoo, TattooDTO.class);
    }


    public ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public Tattoo convertToTattoo(TattooDTO tattooDTO) {
        return modelMapper.map(tattooDTO, Tattoo.class);
    }
}
