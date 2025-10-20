package sdu.coopbank.kb.account.statement.engine.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/api/**",
                                        "/api/accountstatementengine/v1/auth/**",
                                        "/api/accountstatementengine/v1/user/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"
                                ).permitAll()
//                                .requestMatchers("/api/accountstatementengine/v1/user/**").hasAnyRole("ROOT","MANAGER","CREATE_UPDATE","APPROVE","USER", "ADMIN")
                                .requestMatchers("/api/sca/v1/londonslip/**").hasAnyRole("ROOT","MANAGER","CREATE_UPDATE","APPROVE","USER", "ADMIN")
                                .requestMatchers("/api/sca/v1/claim/**").hasAnyRole("ROOT","MANAGER","CREATE_UPDATE","APPROVE","USER", "ADMIN")
                                .requestMatchers("/api/sca/v1/trialbalance/**").hasAnyRole("ADMIN","USER","SUPER_ADMIN","ROOT", "MANAGER")
                                .requestMatchers("/api/v1/management/**").hasAnyRole("ADMIN","SUPER_ADMIN","ROOT", "MANAGER")
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyRole("ADMIN","USER","SUPER_ADMIN","ROOT", "MANAGER")
                                .requestMatchers(POST, "/api/v1/management/**").hasAnyRole("ADMIN","SUPER_ADMIN","ROOT", "MANAGER")
                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyRole("ADMIN","SUPER_ADMIN","ROOT", "MANAGER")
                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyRole("ADMIN","SUPER_ADMIN","ROOT", "MANAGER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                        .permitAll()
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
