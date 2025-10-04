package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
