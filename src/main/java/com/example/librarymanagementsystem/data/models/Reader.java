package com.example.librarymanagementsystem.data.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reader extends LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
