package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Branch;

import java.util.Optional;

public interface BranchesRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByNameIgnoreCase(String name);
    Page<Branch> findAll(Pageable pageable);
    Page<Branch> findAllByNameContainingIgnoreCase(String key1, Pageable pageable);
}
