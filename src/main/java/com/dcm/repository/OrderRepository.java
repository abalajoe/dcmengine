package com.dcm.repository;

import com.dcm.entity.Order;
import com.dcm.entity.Supplier;
import com.dcm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findAllByBuyerid(User user, Pageable pageable);
    Page<Order> findByBuyeridAndSupplier_SkuIgnoreCaseContaining(User user, String name, Pageable pageable);
    Page<Order> findBySupplier_SkuIgnoreCaseContaining(String name, Pageable pageable);
}