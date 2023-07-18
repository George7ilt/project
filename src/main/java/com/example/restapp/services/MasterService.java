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
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepo masterRepo;
    private final TattooService tattooService;
    private final StudioService studioService;
    private final Converter converter;

    public Master findByName(String masterName) {
        Optional<Master> masterOptional = masterRepo.findByName(masterName);
        return masterOptional.orElseThrow(() -> new MasterNotFoundException("Master with that name not found."));
    }

    public MasterDTO findMasterDTOById(long id) {
        Master master = findById(id);
        return converter.convertToMasterDTO(master);
    }

    public Master findById(long id) {
        Optional<Master> masterOpt = masterRepo.findById(id);
        return masterOpt.orElseThrow(() -> new MasterNotFoundException("Master with that id not found."));
    }

    public List<TattooDTO> findMastersWorks(long masterId) {
        Master master = findById(masterId);
        List<Tattoo> tattoos = master.getAvailableTattoos().stream().map(MastersTattoos::getTattoo).toList();
        return tattoos.stream().map(converter::convertToTattooDTO).toList();
    }

    public List<MasterDTO> findMastersBySpecialization(String specialization) {
        List<Master> masters = masterRepo.findAllBySpecialization(specialization);
        return masters.stream().map(converter::convertToMasterDTO).toList();
    }

    public List<MasterDTO> findMastersWithExperienceMoreThan(int exp) {
        List<Master> masters = masterRepo.findAllByExperienceYearsGreaterThan(exp);
        return masters.stream().map(converter::convertToMasterDTO).toList();
    }

    public void deleteById(long masterId) {
        Master master = findById(masterId);
        master.getStudio().getMasters().remove(master);
        masterRepo.deleteById(masterId);
    }

    public void createMaster(MasterDTO masterDTO) {
        Master master = converter.convertToMaster(masterDTO);
        masterRepo.save(master);
    }

    public void addTattooToMasterById(long masterId, long tattooId) {
        Master master = findById(masterId);
        Tattoo tattoo = tattooService.findById(tattooId);
        master.getAvailableTattoos().add(new MastersTattoos(master, tattoo));
        masterRepo.save(master);
    }

    public void deleteTattooFromMasterById(long masterId, long tattooId) {
        Master master = findById(masterId);
        master.getAvailableTattoos().remove(tattooService.findById(tattooId));
        masterRepo.save(master);
    }

    public void setStudioToMaster(long masterId, long studioId) {
        studioService.addMaster(studioId, masterId);
    }

    public void setNewSpec(long masterId, String spec) {
        Master master = findById(masterId);
        master.setSpecialization(spec);
        masterRepo.save(master);
    }

    public List<MasterDTO> findAllMastersByStudioAndSpec(long studioId, String spec) {
        Studio studio = studioService.findStudioById(studioId);
        return masterRepo.findAllByStudioAndSpecialization(studio, spec).stream().map(converter::convertToMasterDTO).toList();
    }

    public void save(Master master) {
        masterRepo.save(master);
    }
}
