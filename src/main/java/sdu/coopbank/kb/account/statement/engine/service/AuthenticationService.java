package sdu.coopbank.kb.account.statement.engine.service;

import sdu.coopbank.kb.account.statement.engine.dto.AuthenticationRequest;
import sdu.coopbank.kb.account.statement.engine.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
