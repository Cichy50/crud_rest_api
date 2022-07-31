package com.cichy.crud_api.controllers;

import com.cichy.crud_api.dto.OwnerDto;
import com.cichy.crud_api.dto.commands.OwnerCommand;
import com.cichy.crud_api.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OwnerController {

    final private OwnerService ownerService;

    @GetMapping("/owners")
    public ResponseEntity<List> getAllOwners() {
        List<OwnerDto> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable(name = "ownerId") Long ownerId) {
        OwnerDto ownerDto = ownerService.getOwnerById(ownerId);
        return ResponseEntity.ok(ownerDto);
    }

    @PostMapping("/owners")
    public ResponseEntity createOwner(@RequestBody OwnerCommand ownerCommand) {
        OwnerDto ownerDto = ownerService.createOwner(ownerCommand);
        return ResponseEntity.status(201).body(ownerDto);
    }

    @PutMapping("/owners/{ownerId}")
    public ResponseEntity updateOwner(@RequestBody OwnerCommand ownerCommand, @PathVariable(name = "ownerId") Long ownerId) {
        OwnerDto ownerDto = ownerService.updateOwner(ownerCommand, ownerId);
        return ResponseEntity.ok(ownerDto);
    }

    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity deleteOwner(@PathVariable(name = "ownerId") Long ownerId) {
        ownerService.deleteOwner(ownerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
