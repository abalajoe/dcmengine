package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Department;

import java.util.Optional;

public interface DepartmentsRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByNameIgnoreCase(String name);
    Page<Department> findAll(Pageable pageable);
    Page<Department> findAllByNameContainingIgnoreCase(String key1, Pageable pageable);
}
