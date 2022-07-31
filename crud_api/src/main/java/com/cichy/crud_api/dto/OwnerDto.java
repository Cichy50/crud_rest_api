package com.cichy.crud_api.dto;

import com.cichy.crud_api.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class OwnerDto {
    private Long ownerId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public static OwnerDto from(Owner owner){
        return OwnerDto.builder()
                .ownerId(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .phoneNumber(owner.getPhoneNumber())
                .build();
    }
}
