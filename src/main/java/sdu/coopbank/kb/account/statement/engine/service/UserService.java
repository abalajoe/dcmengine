package sdu.coopbank.kb.account.statement.engine.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sdu.coopbank.kb.account.statement.engine.dto.UserCreateRequest;
import sdu.coopbank.kb.account.statement.engine.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(UserCreateRequest userCreateRequest);
    User edit(UserCreateRequest userCreateRequest);

    Page<User> findByNameContainingIgnoreCase(String search, Pageable pageable);
    Page<User> findAllPageable(Pageable pageable);
}
