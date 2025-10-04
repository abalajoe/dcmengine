package sdu.coopbank.kb.account.statement.engine.serviceimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.coopbank.kb.account.statement.engine.dto.AuthenticationRequest;
import sdu.coopbank.kb.account.statement.engine.dto.AuthenticationResponse;
import sdu.coopbank.kb.account.statement.engine.entity.User;
import sdu.coopbank.kb.account.statement.engine.exception.EntityExistsException;
import sdu.coopbank.kb.account.statement.engine.repository.UserRepository;
import sdu.coopbank.kb.account.statement.engine.security.JwtService;
import sdu.coopbank.kb.account.statement.engine.service.AuthenticationService;

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
        log.info("authenticatexxxxz {} {}", request.getEmail().trim(), request.getPassword().trim());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().toLowerCase().trim(),
                        request.getPassword().trim()));
        Optional<User> user = userRepository.findByEmail(request.getEmail().toLowerCase().trim());
        // .orElseThrow();
        log.info("user============= {} {}", user, passwordEncoder.encode("joe@123"));
        if (user.isPresent()){
            String jwtToken = jwtService.generateToken(user.get());
            String refreshToken = jwtService.generateRefreshToken(user.get());
            //revokeAllUserTokens(user.get());
            //saveUserToken(user.get(), jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(user.get())
                    .build();
        }
        throw new EntityExistsException("User does not exist");
    }
}
