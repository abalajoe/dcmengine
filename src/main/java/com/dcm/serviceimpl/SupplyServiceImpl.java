package com.dcm.serviceimpl;

import com.dcm.dto.SupplierDTO;
import com.dcm.entity.Supplier;
import com.dcm.entity.User;
import com.dcm.repository.SupplierRepository;
import com.dcm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dcm.exception.EntityExistsException;
import com.dcm.exception.EntityNotExistsException;
import com.dcm.service.SupplyService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplyServiceImpl implements SupplyService {

    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    @Override
    public Supplier createSupplier(SupplierDTO supplierDTO) {
        try {
            log.info("supplierDTO >> {}", supplierDTO);
            Optional<Supplier> supplier = supplierRepository.findBySkuIgnoreCase(supplierDTO.getSku());
            log.info("supplierDTO2 >> {}", supplier);
            if(supplier.isPresent()) throw new EntityExistsException("The resource exists");
            log.info("supplierDTO3 >> {}", supplier);
            Optional<User> user = userRepository.findById(supplierDTO.getUserid());
            if(user.isEmpty()) throw new EntityExistsException("The resource does not exists");
            log.info("user >> {}", user.get());
            Supplier supplier1 = Supplier.builder()
                    .sku(supplierDTO.getSku())
                    .name(supplierDTO.getName())
                    .quantity(supplierDTO.getQuantity())
                    .userid(user.get())
                    .price(supplierDTO.getPrice())
                    .createdby(supplierDTO.getCreatedby())
                    .datecreated(LocalDateTime.now())
                    .status(1)
                    .build();
            log.info("supplier1 >> {}", supplier1);
            return supplierRepository.save(supplier1);
        } catch (Exception e){
            e.printStackTrace();
            log.error("error - {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Supplier updateSupplier(int id, SupplierDTO supplierDTO) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Supplier supplier1 = supplier.get();
        supplier1.setSku(supplierDTO.getSku());
        supplier1.setName(supplierDTO.getName());
        supplier1.setQuantity(supplierDTO.getQuantity());
        supplier1.setPrice(supplierDTO.getPrice());
        return supplierRepository.save(supplier1);
    }

    @Override
    public Supplier deleteSupplier(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Supplier supplier1 = supplier.get();
        supplier1.setStatus(0);
        return supplierRepository.save(supplier1);
    }

    @Override
    public Page<Supplier> findAllSuppliers(int id, Pageable pageable) {
        if (id == 0){
            return supplierRepository.findAllByStatus(1, pageable);
        }
        return supplierRepository.findAllByStatusAndUserid_id(1, id, pageable);
    }

    @Override
    public Page<Supplier> findAllSuppliers(int id, String search, Pageable pageable) {
        return supplierRepository.findAllByStatusAndSkuContainingIgnoreCase(1, search, pageable);
    }

    @Override
    public List<Supplier> findSuppliersReport() {
        return supplierRepository.findAllByStatus(1);
    }
}
