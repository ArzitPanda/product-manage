package com.example.demo.repositories;

import com.example.demo.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    Optional<UserDetails> findByUser_Id(Long id);
    Optional<UserDetails> findByUser_IdOrUser_EmailIgnoreCase(Long id, String email);
}
