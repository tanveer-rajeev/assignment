package com.tanveer.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildResponseDto {
    private String firstName;
    private String lastName;

    private String street;

    private String city;

    private String state;

    private String zip;

}
