package com.dcm.controller;

import com.dcm.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dcm.dto.AuthenticationRequest;
import com.dcm.dto.AuthenticationResponse;

@RestController
@RequestMapping("/api2")
//@RequestMapping("/api/accountstatementengine/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/kongtest")
    public ResponseEntity<String> kongTest(){
        return ResponseEntity.ok("Hello World44");
    }

    @PostMapping("/http-log")
    public void kongLog2(@RequestBody String logData) {
        log.info("log2 - {}", logData);
    }

//    @PostMapping("/authenticate")
    @PostMapping("/userSignin")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("authenticate {}", request);
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        log.info("authenticationResponse {}", authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }
}

