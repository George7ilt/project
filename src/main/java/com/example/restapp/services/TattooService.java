package com.example.restapp.services;

import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.DTO.utils.Converter;
import com.example.restapp.models.Tattoo;
import com.example.restapp.repo.TattooRepo;
import com.example.restapp.util.exceptions.TattooNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TattooService {

    List<?> getAveragePriceOnSpecialization();

    Tattoo findById(long tattooId);

    TattooDTO findTattooDTOById(long tattooID);

    List<TattooDTO> findTattoosByPriceAndSpecialization(String specialization,
                                                               double minPrice,
                                                               double maxPrice);


    void createTattoo(TattooDTO tattooDTO);

    void deleteTattoo(long id);

    List<TattooDTO> getCustomTattoosByQuery(String spec);
}
