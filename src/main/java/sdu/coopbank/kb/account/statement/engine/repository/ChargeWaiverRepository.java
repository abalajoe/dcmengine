package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.ChargeWaiver;

import java.util.Optional;

public interface ChargeWaiverRepository extends JpaRepository<ChargeWaiver, Integer> {
    Page<ChargeWaiver> findAll(Pageable pageable);
    Page<ChargeWaiver> findAllByForacidContainingIgnoreCase(String key1, Pageable pageable);
}
