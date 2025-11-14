package sdu.coopbank.kb.account.statement.engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sdu.coopbank.kb.account.statement.engine.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findBySkuIgnoreCase(String name);
    Page<Supplier> findAll(Pageable pageable);
    Page<Supplier> findAllByStatus(int status, Pageable pageable);
    List<Supplier> findAllByStatus(int status);
    Page<Supplier> findAllByStatusAndSkuContainingIgnoreCase(int status, String sku, Pageable pageable);
}
