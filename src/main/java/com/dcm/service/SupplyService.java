package com.dcm.service;

import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplyService {
    Supplier createSupplier(SupplierDTO supplierDTO);
    Supplier updateSupplier(int id, SupplierDTO supplierDTO);
    Supplier deleteSupplier(int id);
    List<Supplier> findSuppliersReport();
    Page<Supplier> findAllSuppliers(Pageable pageable);
    Page<Supplier> findAllSuppliers(String search, Pageable pageable);
}
