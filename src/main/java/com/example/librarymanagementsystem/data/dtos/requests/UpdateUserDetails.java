package com.example.librarymanagementsystem.data.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDetails {
    private String email;
    private String phoneNumber;
    private int buildingNumber;
    private String street;
    private String city;
    private String state;
    private String country;

}
