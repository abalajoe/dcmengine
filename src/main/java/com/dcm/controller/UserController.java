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

    @GetMapping("/findAllUsers")
    public Page<Supplier> findAllSuppliers(@RequestParam("start") int start,
                                           @RequestParam("length") int length,
                                           @RequestParam(value = "searchVal", required = false) String searchVal,
                                           @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllRoles => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return supplyService.findAllSuppliers(
                    searchVal.trim(), pageable);
        } else {
            return supplyService.findAllSuppliers(pageable);
        }
    }

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

