package com.cichy.crud_api.unit.service;

import com.cichy.crud_api.domain.Owner;
import com.cichy.crud_api.dto.OwnerDto;
import com.cichy.crud_api.dto.commands.OwnerCommand;
import com.cichy.crud_api.repositories.OwnerRepository;
import com.cichy.crud_api.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
public class OwnerServiceTest {

    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerService = new OwnerService(ownerRepository);
    }
    Owner owner = new Owner("Adam","Kowalski","123456789");
    OwnerCommand ownerCommand = new OwnerCommand("Adam","Kowalski","123456789");

    @Test
    void shouldReturnListOfOwnerDto(){
        //given
        when(ownerRepository.findAll()).thenReturn(List.of(owner));
        //when
        List<OwnerDto> ownerDtoList = ownerService.getAllOwners();
        //then
        assertEquals(List.of(OwnerDto.from(owner)),ownerDtoList);
    }

    @Test
    void shouldReturnOwnerDto(){
        //given
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        //when
        OwnerDto ownerDto = ownerService.getOwnerById(1L);
        //then
        assertEquals(OwnerDto.from(owner),ownerDto);
    }

    @Test
    void shouldReturnAddedOwner(){
        //given
        when(ownerRepository.save(any())).thenReturn(owner);
        //when
        OwnerDto ownerDto = ownerService.createOwner(ownerCommand);
        //then
        assertEquals(OwnerDto.from(owner),ownerDto);
    }

    @Test
    void shouldReturnUpdatedOwner(){
        //given
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any())).thenReturn(owner);
        //when
        OwnerDto updatedOwner = ownerService.updateOwner(ownerCommand,1L);
        //then
        assertEquals(OwnerDto.from(owner),updatedOwner);
    }
}
