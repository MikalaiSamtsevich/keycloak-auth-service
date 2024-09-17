package com.techopt.authservice.config;

import com.techopt.authservice.util.KeycloakJwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enable method-level security (need roles to access specific endpoints)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/auth/roles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/users").permitAll()
                        .requestMatchers("/auth/users/forgot-password").permitAll()
                        .requestMatchers("/auth/users/{id}/send-verification-email").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/groups").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oath2 -> oath2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)
                        )
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> {
//            web.ignoring().requestMatchers(
//                    HttpMethod.POST,
//                    "/public/**",
//                    "/auth/users"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.GET,
//                    "/public/**"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.PUT,
//                    "/public/**",
//                    "/auth/users/{id}/send-verification-email"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.PATCH,
//                    "/public/**",
//                    "/auth/users/forgot-password"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.DELETE,
//                    "/public/**"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.OPTIONS,
//                    "/**"
//            ).requestMatchers("/v3/api-docs/**", "/configuration/**", "/swagger-ui/**",
//                    "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**");
//        };
//    }
}
