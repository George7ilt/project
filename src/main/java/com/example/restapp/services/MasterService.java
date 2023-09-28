package com.example.restapp.services;

import com.example.restapp.DTO.MasterDTO;
import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Master;
import com.example.restapp.models.Studio;
import com.example.restapp.models.Tattoo;
import com.example.restapp.models.relations.MastersTattoos;
import com.example.restapp.repo.MasterRepo;
import com.example.restapp.util.exceptions.MasterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MasterService {

    Master findByName(String masterName);

    MasterDTO findMasterDTOById(long id);

    Master findById(long id);

    List<TattooDTO> findMastersWorks(long masterId);

    List<MasterDTO> findMastersBySpecialization(String specialization);

    List<MasterDTO> findMastersWithExperienceMoreThan(int exp);

    void deleteById(long masterId);

    void createMaster(MasterDTO masterDTO);

    void addTattooToMasterById(long masterId, long tattooId);

    void deleteTattooFromMasterById(long masterId, long tattooId);

    void setStudioToMaster(long masterId, long studioId);

    void setNewSpec(long masterId, String spec);

    List<MasterDTO> findAllMastersByStudioAndSpec(long studioId, String spec);

    void save(Master master);
}
