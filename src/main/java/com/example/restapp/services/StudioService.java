package com.example.restapp.services;

import com.example.restapp.DTO.MasterDTO;
import com.example.restapp.DTO.StudioDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Master;
import com.example.restapp.models.Studio;
import com.example.restapp.repo.StudioRepo;
import com.example.restapp.util.exceptions.StudioNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudioService {

    Studio findStudioById(long studioId);

    StudioDTO findStudioDTOById(long studioId);

    void create(StudioDTO studioDTO);

    void deleteStudio(long id);

    List<MasterDTO> findAllMasters(long id);

    void setNewAdress(long id, String newAdress);

    void setNewName(long id, String newName);

    void addMaster(long id, long masterId);

    void setNewNumber(long id, String newNumber);

    void fireMaster(long studioId, long masterId);
}
