package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
