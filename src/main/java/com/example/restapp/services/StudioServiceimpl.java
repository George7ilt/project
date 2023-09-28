package com.example.restapp.services;

import com.example.restapp.DTO.MasterDTO;
import com.example.restapp.DTO.StudioDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Master;
import com.example.restapp.models.Studio;
import com.example.restapp.repo.StudioRepo;
import com.example.restapp.util.exceptions.StudioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudioServiceimpl implements StudioService{
    @Autowired
    private StudioRepo studioRepo;
    @Autowired
    private MasterService masterService;
    @Autowired
    private Converter converter;

    public StudioServiceimpl(StudioRepo studioRepo, @Lazy MasterService masterService, Converter converter) {
        this.studioRepo = studioRepo;
        this.masterService = masterService;
        this.converter = converter;
    }

    public Studio findStudioById(long studioId) {
        Optional<Studio> optionalStudio = studioRepo.findById(studioId);
        return optionalStudio.orElseThrow(() -> new StudioNotFoundException("Studio with that id not found"));
    }

    public StudioDTO findStudioDTOById(long studioId) {
        Optional<Studio> optionalStudio = studioRepo.findById(studioId);
        Studio studio = optionalStudio.orElseThrow(() -> new StudioNotFoundException("Studio with that id not found"));
        return converter.convertToStudioDTO(studio);
    }

    public void create(StudioDTO studioDTO) {
        Studio studio = converter.convertToStudio(studioDTO);
        studioRepo.save(studio);
    }

    public void deleteStudio(long id) {
        studioRepo.deleteById(id);
    }

    public List<MasterDTO> findAllMasters(long id) {
        return findStudioById(id).getMasters().stream().map(converter::convertToMasterDTO).toList();
    }

    public void setNewAdress(long id, String newAdress) {
        Studio studio = findStudioById(id);
        studio.setAddress(newAdress);
        studioRepo.save(studio);
    }

    public void setNewName(long id, String newName) {
        Studio studio = findStudioById(id);
        studio.setAddress(newName);
        studioRepo.save(studio);
    }

    public void addMaster(long id, long masterId) {
        Master master = masterService.findById(masterId);
        master.getStudio().getMasters().remove(master);
        Studio studio = findStudioById(id);
        studio.getMasters().add(master);
        studioRepo.save(studio);
    }

    public void setNewNumber(long id, String newNumber) {
        Studio studio = findStudioById(id);
        studio.setContactNumber(newNumber);
    }

    public void fireMaster(long studioId, long masterId) {
        Studio studio = findStudioById(studioId);
        Master master = masterService.findById(masterId);
        studio.getMasters().remove(master);

    }
}
