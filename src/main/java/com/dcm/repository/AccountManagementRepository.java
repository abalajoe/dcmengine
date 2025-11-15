package com.dcm.repository;

import com.dcm.entity.AccountManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountManagementRepository extends JpaRepository<AccountManagement, Integer> {
    Page<AccountManagement> findAll(Pageable pageable);

    Page<AccountManagement> findByEmailContainingIgnoreCase(
            String searchTerm,
            Pageable pageable
    );

    Page<AccountManagement> findByEmailContainingIgnoreCaseOrBranchContainingIgnoreCaseOrManagerContainingIgnoreCase(
            String searchTerm,
            String searchTerm2,
            String searchTerm3,
            Pageable pageable
    );
}
