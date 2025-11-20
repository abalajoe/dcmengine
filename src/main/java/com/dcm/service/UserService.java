package com.dcm.service;

import com.dcm.dto.SupplierDTO;
import com.dcm.dto.UserDTO;
import com.dcm.entity.Supplier;
import com.dcm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User create(UserDTO userDTO);
}
