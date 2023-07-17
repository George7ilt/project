package com.example.restapp.controllers;

import com.example.restapp.DTO.TattooDTO;
import com.example.restapp.services.TattooService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tattoos")
public class TattooController {
    private final TattooService tattooService;


    //    посмотреть список татух по цене и по специализации мастера
    @GetMapping("/{masterSpec}/price_between")
    public ResponseEntity<?> getTattoosByPriceAndSpec(@PathVariable String masterSpec,
                                                      @RequestParam(required = false, defaultValue = "0") double minPrice,
                                                      @RequestParam(required = false, defaultValue = "" + Double.MAX_VALUE) double maxPrice) {
        return ResponseEntity.ok(tattooService.findTattoosByPriceAndSpecialization(masterSpec, minPrice, maxPrice));
    }


    //добавить тату
    @PostMapping("/create")
    public ResponseEntity<?> createTattoo(@RequestBody @Valid TattooDTO tattooDTO) {
        tattooService.createTattoo(tattooDTO);
        return ResponseEntity.ok("tattoo created");
    }


    //удалить тату
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTattoo(@PathVariable long id) {
        tattooService.deleteTattoo(id);
        return ResponseEntity.ok("tattoo deleted");
    }


    //    Получить все татуировки, которые стоят меньше 5000 рублей и имеют мастера со специализацией .
    @GetMapping
    public ResponseEntity<?> getCustomTattoosByQuery(@RequestParam(required = true) String spec) {
        return ResponseEntity.ok(tattooService.getCustomTattoosByQuery(spec));
    }

}
