package com.example.librarymanagementsystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String authority;
    @Enumerated
    private AuthorityType authorityType;

    public Authority(AuthorityType authorityType){
        this.authorityType = authorityType;
    }

}
