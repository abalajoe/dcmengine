package com.dcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dcm.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
