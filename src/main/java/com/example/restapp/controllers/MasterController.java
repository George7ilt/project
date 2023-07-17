package com.example.restapp.controllers;

import com.example.restapp.DTO.MasterDTO;
import com.example.restapp.services.MasterService;
import com.example.restapp.services.TattooService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final TattooService tattooService;

    //    посмотреть самого мастера  ++++++++++++++
    @GetMapping("/{id}")
    public ResponseEntity<?> getMaster(@PathVariable long id) {
        return ResponseEntity.ok(masterService.findMasterDTOById(id));
    }

    //    посмотреть список работ мастера ++++++++++++++
    @GetMapping("/{id}/works")
    public ResponseEntity<?> getMastersWorks(@PathVariable long id) {
        return ResponseEntity.ok(masterService.findMastersWorks(id));
    }

    //    посмотреть всех мастеров по специализации
    @GetMapping
    public ResponseEntity<?> getAllMastersOnSpecialization(@RequestParam(required = false) String specialization) {
        return ResponseEntity.ok(masterService.findMastersBySpecialization(specialization));
    }


    //посмотреть всех мастеров по опыту (больше чем int)
    @GetMapping("/{exp}")
    public ResponseEntity<?> getMastersWithExperienceMoreThan(@PathVariable int exp) {
        return ResponseEntity.ok(masterService.findMastersWithExperienceMoreThan(exp));
    }


    //добавить мастеру тату
    @PatchMapping("/{masterId}/addTattoo/{tattooId}")
    public ResponseEntity<?> addTattooToMaster(@PathVariable long masterId,
                                               @PathVariable long tattooId) {
        masterService.addTattooToMasterById(masterId, tattooId);
        return ResponseEntity.ok("Tattoo added");
    }

    //обновить студию мастера
    @PatchMapping("/{masterId}/setStudio/{studioId}")
    public ResponseEntity<?> setStudioToMaster(@PathVariable long masterId,
                                               @PathVariable long studioId) {
        masterService.setStudioToMaster(masterId, studioId);
        return ResponseEntity.ok("Master's studio updated");
    }

    //обновить специализацию мастера
    @PatchMapping("/{masterId}/setSpecialization/{spec}")
    public ResponseEntity<?> setStudioToMaster(@PathVariable long masterId,
                                               @PathVariable String spec) {
        masterService.setNewSpec(masterId, spec);
        return ResponseEntity.ok("Master's specialization updated");
    }


    //удалить тату у мастера
    @DeleteMapping("/{masterId}/removeTattoo/{tattooId}")
    public ResponseEntity<?> deleteTattooFromMaster(@PathVariable long masterId,
                                                    @PathVariable long tattooId) {
        masterService.deleteTattooFromMasterById(masterId, tattooId);
        return ResponseEntity.ok("Tattoo deleted from master's list");
    }


    //создать мастера
    @PostMapping("/create")
    public ResponseEntity<?> createMaster(@RequestBody @Valid MasterDTO masterDTO) {
        masterService.createMaster(masterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Master created");
    }

    //удалить мастера
    @DeleteMapping("/{masterId}")
    public ResponseEntity<?> deleteMaster(@PathVariable long masterId) {
        masterService.deleteById(masterId);
        return ResponseEntity.ok("Master removed.");
    }


    //  Получить среднюю стоимость татуировок для каждой специализации мастеров.
    @GetMapping("/averagePriceOnSpecializations")
    public ResponseEntity<?> getAveragePriceOfTattoosOnSpecializations() {
        tattooService.getAveragePriceOnSpecialization();
        return ResponseEntity.ok(tattooService.getAveragePriceOnSpecialization());
    }
}
