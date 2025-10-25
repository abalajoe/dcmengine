package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Manager;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findByNameIgnoreCase(String name);
    Page<Manager> findAll(Pageable pageable);
    Page<Manager> findAllByNameContainingIgnoreCase(String key1, Pageable pageable);
}
