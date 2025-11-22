package com.dcm.controller;

import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dcm.service.SupplyService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {
    private final SupplyService supplyService;

    @GetMapping("/findAllSuppliers")
    public Page<Supplier> findAllSuppliers(@RequestParam("id") int id,
                                           @RequestParam("start") int start,
                                           @RequestParam("length") int length,
                                           @RequestParam(value = "searchVal", required = false) String searchVal,
                                           @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllSuppliers => id={} start={} length={} sort={} searchVal={}",
                id, start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
        //  Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return supplyService.findAllSuppliers(id,
                    searchVal.trim(), pageable);
        } else {
            return supplyService.findAllSuppliers(id,pageable);
        }
    }

    @GetMapping("/supplierReport")
    public ResponseEntity<List<Supplier>> findSuppliersReport() {
        List<Supplier> suppliers = supplyService.findSuppliersReport();
        log.info("suppliers {}", suppliers);
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> create(@RequestBody SupplierDTO supplierDTO) {
        log.info("supplierDTO {}", supplierDTO);

        Supplier supplier = supplyService.createSupplier(supplierDTO);
        log.info("department {}", supplier);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable int id,
            @RequestBody SupplierDTO supplierDTO) {

        Supplier updated = supplyService.updateSupplier(id, supplierDTO);
        log.info("supplier {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<Supplier> deleteSupplier(
            @PathVariable int id) {

        Supplier updated = supplyService.deleteSupplier(id);
        log.info("supplier {}", updated);
        return ResponseEntity.ok(updated);
    }
}

