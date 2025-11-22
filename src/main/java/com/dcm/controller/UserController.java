package com.dcm.controller;

import com.dcm.dto.SupplierDTO;
import com.dcm.dto.UserDTO;
import com.dcm.entity.Supplier;
import com.dcm.entity.User;
import com.dcm.service.SupplyService;
import com.dcm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final SupplyService supplyService;
    private final UserService userService;

    @GetMapping("/userReport")
    public ResponseEntity<List<Supplier>> findSuppliersReport() {
        List<Supplier> suppliers = supplyService.findSuppliersReport();
        log.info("suppliers {}", suppliers);
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping("/user")
    public ResponseEntity<Supplier> create(@RequestBody SupplierDTO supplierDTO) {
        log.info("supplierDTO {}", supplierDTO);

        Supplier supplier = supplyService.createSupplier(supplierDTO);
        log.info("department {}", supplier);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        log.info("userDTO {}", userDTO);

        User user = userService.create(userDTO);
        log.info("user {}", user);
        return ResponseEntity.ok(user);
    }
}

