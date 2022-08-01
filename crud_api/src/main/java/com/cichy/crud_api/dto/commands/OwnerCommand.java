package com.cichy.crud_api.dto.commands;

import lombok.Value;

@Value
public class OwnerCommand {
    private String firstName;

    private String lastName;

    private String phoneNumber;
}
