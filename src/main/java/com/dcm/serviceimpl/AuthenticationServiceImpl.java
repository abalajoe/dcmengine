package com.dcm.serviceimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dcm.dto.AuthenticationRequest;
import com.dcm.dto.AuthenticationResponse;
import com.dcm.entity.User;
import com.dcm.exception.EntityExistsException;
import com.dcm.repository.UserRepository;
import com.dcm.security.JwtService;
import com.dcm.service.AuthenticationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("user=============101 {} ", passwordEncoder.encode("joe@123"));
        log.info("authenticatexxxxz {} {}", request.getEmail().trim(), request.getPswrd().trim());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().toLowerCase().trim(),
                        request.getPswrd().trim().toLowerCase()));
        Optional<User> user = userRepository.findByEmail(request.getEmail().toLowerCase().trim());
        // .orElseThrow();
        log.info("user============= {} {}", user, passwordEncoder.encode("joe@123"));
        if (user.isPresent()){
            String jwtToken = jwtService.generateToken(user.get());
            String refreshToken = jwtService.generateRefreshToken(user.get());
            //revokeAllUserTokens(user.get());
            //saveUserToken(user.get(), jwtToken);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("roleName", user.get().getRoleName());
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .email(user.get().getEmail())
                    .roleName(jsonObject.toString())
                    .user(user.get())
                    .build();
        }
        throw new EntityExistsException("User does not exist");
    }
}
