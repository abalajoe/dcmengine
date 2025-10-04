package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Page<User> findByNameContainingIgnoreCase(
            String searchTerm,
            Pageable pageable
    );

    Page<User> findAll(Pageable pageable);
}
