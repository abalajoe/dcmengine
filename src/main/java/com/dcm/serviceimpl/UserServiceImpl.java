package com.dcm.serviceimpl;

import com.dcm.dto.UserDTO;
import com.dcm.entity.Role;
import com.dcm.entity.User;
import com.dcm.exception.EntityExistsException;
import com.dcm.repository.RoleRepository;
import com.dcm.repository.UserRepository;
import com.dcm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserDTO userDTO) {
        log.info("userDTO {}", userDTO);
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent()) throw new EntityExistsException("The user exists");

        Optional<Role> role = roleRepository.findById(1);
        User user1 = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roleName(userDTO.getRolename())
                .roles(role.get())
                .datecreated(new Date())
                .build();
        return userRepository.save(user1);
    }
}
