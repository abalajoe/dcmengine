package com.dcm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcm.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Page<User> findByNameContainingIgnoreCase(
            String searchTerm,
            Pageable pageable
    );

    Page<User> findAll(Pageable pageable);
}
