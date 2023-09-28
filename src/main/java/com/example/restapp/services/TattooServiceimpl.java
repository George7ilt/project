package com.example.restapp.services;

import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Tattoo;
import com.example.restapp.repo.TattooRepo;
import com.example.restapp.util.exceptions.TattooNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TattooServiceimpl implements TattooService {
    @Autowired
    private TattooRepo tattooRepo;
    @Autowired
    private Converter converter;

    public List<?> getAveragePriceOnSpecialization() {
        return tattooRepo.getAveragePriceOnSpecializations();
    }

    public Tattoo findById(long tattooId) {
        Optional<Tattoo> tattooOptional = tattooRepo.findById(tattooId);
        return tattooOptional.orElseThrow(() -> new TattooNotFoundException("Tattoo not found"));
    }

    public TattooDTO findTattooDTOById(long tattooID) {
        Optional<Tattoo> tattooOptional = tattooRepo.findById(tattooID);
        Tattoo tattoo = tattooOptional.orElseThrow(() -> new TattooNotFoundException("Tattoo not found"));
        return converter.convertToTattooDTO(tattoo);
    }

    public List<TattooDTO> findTattoosByPriceAndSpecialization(String specialization,
                                                               double minPrice,
                                                               double maxPrice) {
        List<Tattoo> tattoos = tattooRepo.findTattoosByPriceAndSpecialization(specialization, minPrice, maxPrice);
        return tattoos.stream().map(converter::convertToTattooDTO).toList();
    }


    public void createTattoo(TattooDTO tattooDTO) {
        tattooRepo.save(converter.convertToTattoo(tattooDTO));
    }

    public void deleteTattoo(long id) {
        tattooRepo.deleteById(id);
    }

    public List<TattooDTO> getCustomTattoosByQuery(String spec) {
        return tattooRepo.getCustomTattoosByQuery(spec).stream().map(converter::convertToTattooDTO).toList();
    }
}
