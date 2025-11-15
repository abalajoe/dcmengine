package com.dcm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcm.entity.Roles;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByNameIgnoreCase(String name);
    Page<Roles> findAll(Pageable pageable);
    Page<Roles> findAllByNameContainingIgnoreCase(String key1, Pageable pageable);
}
