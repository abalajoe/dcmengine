package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.PrintHistory;
import sdu.coopbank.kb.account.statement.engine.entity.User;

public interface PrintHistoryRepository extends JpaRepository<PrintHistory, Integer> {
    Page<PrintHistory> findAll(Pageable pageable);

    Page<PrintHistory> findByEmailContainingIgnoreCase(
            String searchTerm,
            Pageable pageable
    );
}
