package com.example.restapp.controllers;

import com.example.restapp.DTO.StudioDTO;
import com.example.restapp.services.MasterService;
import com.example.restapp.services.StudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/studios")
public class StudioController {
    private final StudioService studioService;
    private final MasterService masterService;

    //    создать студию
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid StudioDTO studioDTO) {
        studioService.create(studioDTO);
        return ResponseEntity.ok("Studio created");
    }

    //удалить студию
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudio(@PathVariable long id) {
        studioService.deleteStudio(id);
        return ResponseEntity.ok("Studio removed.");
    }


    //изменить адрес студии
    @PatchMapping("/{id}/set_new_adress")
    public ResponseEntity<?> getStudio(@PathVariable long id,
                                       @RequestBody String newAdress) {
        studioService.setNewAdress(id, newAdress);
        return ResponseEntity.ok("Address updated");
    }

    //изменить имя студии
    @PatchMapping("/{id}/set_new_name")
    public ResponseEntity<?> setNewName(@PathVariable long id,
                                        @RequestBody String newName) {
        studioService.setNewName(id, newName);
        return ResponseEntity.ok("Studio's name updated");
    }

    //добавить мастера в студию
    @PatchMapping("/{id}/add_master/{masterId}")
    public ResponseEntity<?> addMaster(@PathVariable long id,
                                       @PathVariable long masterId) {
        studioService.addMaster(id, masterId);
        return ResponseEntity.ok("Master added");
    }

    //удалить мастера из студии
    @DeleteMapping("/{stusioId}/remove_master/{masterId}")
    public ResponseEntity<?> removeMaster(@PathVariable long stusioId,
                                          @PathVariable long masterId) {
        studioService.fireMaster(stusioId, masterId);
        return ResponseEntity.ok("Master fired");
    }

    //обновить номер телефона студии
    @PatchMapping("/{id}")
    public ResponseEntity<?> setNewNumber(@PathVariable long id,
                                          @RequestBody String newNumber) {
        studioService.setNewNumber(id, newNumber);
        return ResponseEntity.ok("New number added");
    }

    //    посмотреть студию
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudio(@PathVariable long id) {
        return ResponseEntity.ok(studioService.findStudioDTOById(id));
    }

    //    посмотреть список мастеров в студии
    @GetMapping("/{id}/masters")
    public ResponseEntity<?> getMastersFromStudio(@PathVariable long id) {
        return ResponseEntity.ok(studioService.findAllMasters(id));
    }

    //    посмотреть список мастеров в студии по специализации
    @GetMapping("/{id}/{spec}")
    public ResponseEntity<?> getMastersFromStudioBySpec(@PathVariable long id,
                                                        @PathVariable String spec) {
        return ResponseEntity.ok(masterService.findAllMastersByStudioAndSpec(id, spec));
    }

}
