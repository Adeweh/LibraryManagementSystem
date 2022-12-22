package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.data.models.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findByEmail(String email);
}
