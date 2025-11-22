package com.dcm.service;

import com.dcm.dto.OrderDTO;
import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Order;
import com.dcm.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDTO orderDTO);
    List<Order> findOrdersReport();
    Page<Order> findAllOrders(int id, Pageable pageable);
    Page<Order> findAllOrders(int id, String search, Pageable pageable);

}