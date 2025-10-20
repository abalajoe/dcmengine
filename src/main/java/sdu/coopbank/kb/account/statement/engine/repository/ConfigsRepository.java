package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Configs;

public interface ConfigsRepository extends JpaRepository<Configs, Integer> {
    Page<Configs> findAll(Pageable pageable);
    Page<Configs> findAllByParamContainingIgnoreCase(String key1, Pageable pageable);
}
