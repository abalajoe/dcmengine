package com.dcm.serviceimpl;

import com.dcm.dto.OrderDTO;
import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Order;
import com.dcm.entity.Supplier;
import com.dcm.entity.User;
import com.dcm.exception.EntityExistsException;
import com.dcm.exception.EntityNotExistsException;
import com.dcm.repository.OrderRepository;
import com.dcm.repository.SupplierRepository;
import com.dcm.repository.UserRepository;
import com.dcm.service.OrderService;
import com.dcm.service.SupplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Optional<User> seller = userRepository.findById(orderDTO.getSellerid());
        log.info("seller {}", seller);
        Optional<User> buyer = userRepository.findById(orderDTO.getBuyerid());
        log.info("buyer {}", buyer);
        Optional<Supplier> supplier = Optional.empty();
        Optional<Order> _order = Optional.empty();;

        supplier = supplierRepository.findById(orderDTO.getItemid());
        log.info("supplier {}", supplier);
        _order = orderRepository.findById(orderDTO.getItemid());
        log.info("order {}", _order);

        if (seller.isEmpty()) throw new EntityNotExistsException("Seller does not exist");
        if (buyer.isEmpty()) throw new EntityNotExistsException("Buyer does not exist");
        if (supplier.isEmpty() && _order.isEmpty()) throw new EntityNotExistsException("Supplier/Order does not exist");

        // supplier.ifPresent(value -> value.setQuantity(orderDTO.getQuantity()));
        // _order.ifPresent(value -> value.setQuantity(orderDTO.getQuantity()));
        if (supplier.isPresent()){
            supplier.get().setQuantity(supplier.get().getQuantity() - orderDTO.getQuantity());
            supplierRepository.save(supplier.get());
        }

        if (_order.isPresent()){
            log.info("order is present ---- {} {}", _order.get().getQuantity(),orderDTO.getQuantity() );
            _order.get().setQuantity(_order.get().getQuantity() - orderDTO.getQuantity());
            orderRepository.save(_order.get());
        }
        Order order = Order.builder()
                .sellerid(seller.get())
                .buyerid(buyer.get())
                .supplier(supplier.orElse(null))
                .orders(_order.orElse(null))
                .quantity(orderDTO.getQuantity())
                .price(orderDTO.getPrice())
                .status(1)
                .datecreated(LocalDateTime.now())
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order updatePrice(int id, double price) {
        log.info("updatePrice {} {}", id, price);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) throw new EntityNotExistsException("Entity does not exist");

        Order order1 = order.get();
        order1.setPrice(price);
        return orderRepository.save(order1);
    }

    @Override
    public List<Order> findOrdersReport() {
        return List.of();
    }

    @Override
    public Page<Order> findAllOrders(int id, Pageable pageable) {
        if (id == 0){
            return orderRepository.findAll(pageable);
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");
        return orderRepository.findAllByBuyerid(user.get(), pageable);
    }

    @Override
    public Page<Order> findAllOrders(int id, String search, Pageable pageable) {
        if (id == 0){
            return orderRepository.findBySupplier_SkuIgnoreCaseContaining(search, pageable);
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");
        return orderRepository.findByBuyeridAndSupplier_SkuIgnoreCaseContaining(user.get(), search, pageable);
    }

        @Override
    public Page<Order> findAllOrdersRetailers(int id, Pageable pageable) {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");
        return orderRepository.findAllByBuyeridNot(user.get(), pageable);
    }

    @Override
    public Page<Order> findAllOrdersRetailers(int id, String search, Pageable pageable) {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");
        return orderRepository.findByBuyeridNotAndSupplier_SkuIgnoreCaseContaining(user.get(), search, pageable);
    }
}

