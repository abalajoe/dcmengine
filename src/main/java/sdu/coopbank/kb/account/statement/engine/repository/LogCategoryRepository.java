package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.LogCategory;

import java.util.Optional;

public interface LogCategoryRepository extends JpaRepository<LogCategory, Integer> {
    Optional<LogCategory> findByNameIgnoreCase(String name);
    Page<LogCategory> findAll(Pageable pageable);
    Page<LogCategory> findAllByNameContainingIgnoreCase(String key1, Pageable pageable);
}
