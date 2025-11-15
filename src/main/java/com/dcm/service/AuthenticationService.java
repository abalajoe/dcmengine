package com.dcm.service;

import com.dcm.dto.AuthenticationRequest;
import com.dcm.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
