package com.dcm.controller;

import com.dcm.dto.OrderDTO;
import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Order;
import com.dcm.entity.Supplier;
import com.dcm.service.OrderService;
import com.dcm.service.SupplyService;
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
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/findAllOrders")
    public Page<Order> findAllSuppliers(@RequestParam("id") int id,
                                        @RequestParam("start") int start,
                                        @RequestParam("length") int length,
                                        @RequestParam(value = "searchVal", required = false) String searchVal,
                                        @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllOrders => id={} start={} length={} sort={} searchVal={}",
                id, start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return orderService.findAllOrders(id,
                    searchVal.trim(), pageable);
        } else {
            return orderService.findAllOrders(id, pageable);
        }
    }

    @GetMapping("/findAllOrdersRetailers")
    public Page<Order> findAllOrdersRetailers(@RequestParam("id") int id,
                                        @RequestParam("start") int start,
                                        @RequestParam("length") int length,
                                        @RequestParam(value = "searchVal", required = false) String searchVal,
                                        @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllOrders => id={} start={} length={} sort={} searchVal={}",
                id, start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return orderService.findAllOrdersRetailers(id,
                    searchVal.trim(), pageable);
        } else {
            return orderService.findAllOrdersRetailers(id, pageable);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO) {
        log.info("orderDTO {}", orderDTO);

        Order order = orderService.createOrder(orderDTO);
        log.info("order {}", order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/order/{id}/{price}")
    public ResponseEntity<Order> updateSupplier(
            @PathVariable int id,
            @PathVariable double price) {

        Order updated = orderService.updatePrice(id, price);
        log.info("order {}", updated);
        return ResponseEntity.ok(updated);
    }
}
