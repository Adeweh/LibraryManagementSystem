package com.example.librarymanagementsystem.data.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDetailsResponse {
    private String email;
    private String message;
}
