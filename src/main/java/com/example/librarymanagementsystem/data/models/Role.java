package com.example.librarymanagementsystem.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated
    private RoleType roleType;
    @OneToMany
    @JoinColumn(name = "authority_id")
    private Set<Authority> authorities;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}
