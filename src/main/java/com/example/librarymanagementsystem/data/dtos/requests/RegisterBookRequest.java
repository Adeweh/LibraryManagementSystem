package com.example.librarymanagementsystem.data.dtos.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterBookRequest {
    private String title;
    private String author;
    private Long isbn;
    private String edition;

}
