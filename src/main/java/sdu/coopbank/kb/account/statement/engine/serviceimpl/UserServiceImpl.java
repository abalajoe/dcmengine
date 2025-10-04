package sdu.coopbank.kb.account.statement.engine.serviceimpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.coopbank.kb.account.statement.engine.dto.UserCreateRequest;
import sdu.coopbank.kb.account.statement.engine.entity.Role;
import sdu.coopbank.kb.account.statement.engine.entity.User;
import sdu.coopbank.kb.account.statement.engine.exception.EntityNotExistsException;
import sdu.coopbank.kb.account.statement.engine.repository.RoleRepository;
import sdu.coopbank.kb.account.statement.engine.repository.UserRepository;
import sdu.coopbank.kb.account.statement.engine.service.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateRequest userCreateRequest) {

        Optional<Role> role = roleRepository.findByName(userCreateRequest.getRole());
        if (role.isEmpty()) throw new EntityNotExistsException("The entity is not found");
        User user = User.builder()
                .name(userCreateRequest.getEmail())
                .email(userCreateRequest.getEmail())
                .datecreated(new Date())
                .roles(role.get())
                .password(passwordEncoder.encode("joe@123"))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User edit(UserCreateRequest userCreateRequest) {
        Optional<User> user = userRepository.findById(userCreateRequest.getId());
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        User user1 = user.get();
        user1.setName(userCreateRequest.getEmail());
        return userRepository.save(user1);
    }

    @Override
    public Page<User> findByNameContainingIgnoreCase(String search, Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCase(search,pageable);
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
