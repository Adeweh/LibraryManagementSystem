package com.example.librarymanagementsystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<Role> roles;
    private String phoneNumber;
    private String street;
    private int buildingNumber;
    private String city;
    private String state;
    private String country;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payment> payments;

    public LibraryUser(String firstName, String lastName, String email, String password, RoleType roleType){
        this.firstName =firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        if(roles == null){
            roles = new HashSet<>();
            roles.add(new Role(roleType));
        }
    }
}
