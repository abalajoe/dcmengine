package sdu.coopbank.kb.account.statement.engine.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sdu.coopbank.kb.account.statement.engine.repository.TokenRepository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * OncePerRequestFilter - Triggered on each request
 */
@Component
@RequiredArgsConstructor // creates constructor using any final field
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        //logPostOrPutRequestBody((HttpServletRequest) request);
        // get bearer token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        log.info("one {}", authHeader);
        // validate bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response); // pass request to next filter and return
            return;
        }
        log.info("one1");
        // extract token and email from authentication header
        jwt = authHeader.substring(7);
        try {
            log.info("one2");
            userEmail = jwtService.extractUsername(jwt); // May throw ExpiredJwtException
            log.info("one3 {}", userEmail);
        } catch (ExpiredJwtException ex) {
            log.info("one4");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("JWT token has expired.");
            return;
        } catch (Exception ex) {
            log.info("one5");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("Invalid JWT token.");
            return;
        }

        // check if user is authenticated, if yes we do not have to get username from db and validate token
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("----");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("user109 {}" + userDetails);
            // check if token valid
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                // token valid, update security context and send request to servlet dispatcher
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // user authenticated, pass to next filter
        filterChain.doFilter(request, response);
    }

    private void logPostOrPutRequestBody(HttpServletRequest httpRequest) throws IOException {
        if(Arrays.asList("POST", "PUT").contains(httpRequest.getMethod())) {
            String characterEncoding = httpRequest.getCharacterEncoding();
            Charset charset = Charset.forName(characterEncoding);
            String bodyInStringFormat = readInputStreamInStringFormat(httpRequest.getInputStream(), charset);
            log.info("Request body: {}", bodyInStringFormat);
        }
    }

    private String readInputStreamInStringFormat(InputStream stream, Charset charset) throws IOException {
        final int MAX_BODY_SIZE = 1024;
        final StringBuilder bodyStringBuilder = new StringBuilder();
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }

        stream.mark(MAX_BODY_SIZE + 1);
        final byte[] entity = new byte[MAX_BODY_SIZE + 1];
        final int bytesRead = stream.read(entity);

        if (bytesRead != -1) {
            bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE), charset));
            if (bytesRead > MAX_BODY_SIZE) {
                bodyStringBuilder.append("...");
            }
        }
        stream.reset();

        return bodyStringBuilder.toString();
    }
}
