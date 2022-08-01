package com.cichy.crud_api.services;

import com.cichy.crud_api.domain.Owner;
import com.cichy.crud_api.dto.OwnerDto;
import com.cichy.crud_api.dto.commands.OwnerCommand;
import com.cichy.crud_api.exeptions.ResourceNotFoundException;
import com.cichy.crud_api.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    final private OwnerRepository ownerRepository;

    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll()
                .stream().map(OwnerDto::from).toList();

    }

    public OwnerDto getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .map(OwnerDto::from)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found!"));
    }

    public OwnerDto createOwner(OwnerCommand ownerCommand) {
        Owner owner = Owner.builder()
                .firstName(ownerCommand.getFirstName())
                .lastName(ownerCommand.getLastName())
                .phoneNumber(ownerCommand.getPhoneNumber())
                .build();
        return OwnerDto.from(ownerRepository.save(owner));
    }

    public OwnerDto updateOwner(OwnerCommand ownerCommand, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .map(o -> Owner.updateOwner(o, ownerCommand))
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found!"));
        return OwnerDto.from(ownerRepository.save(owner));
    }

    public void deleteOwner(Long ownerId) {
        if (!ownerRepository.existsById(ownerId)) {
            throw new ResourceNotFoundException("Owner not found!");
        } else ownerRepository.deleteById(ownerId);
    }
}
