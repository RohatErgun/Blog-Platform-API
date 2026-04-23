package com.rohater.blog.security;

import com.rohater.blog.repository.UserRepository;
import com.rohater.blog.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /**
     * Registers the custom JWT authentication filter used to intercept incoming HTTP requests
     * and extract the Bearer token from the Authorization header.
     *
     * The filter validates the token and sets the authenticated user inside the
     * Spring Security context before request processing continues.
     *
     * This enables stateless authentication across secured endpoints.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){
        return new JwtAuthenticationFilter(authenticationService);
    }

    /**
     * Provides a custom implementation of UserDetailsService used by Spring Security
     * to load user-specific data during authentication.
     *
     * This service retrieves users from the database via UserRepository and adapts
     * them into BlogUserDetails objects understood by Spring Security.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new BlogUserDetailsService(userRepository);
    }

    /**
     * Configures the DAO-based authentication provider responsible for validating
     * username/password credentials during authentication.
     *
     * It uses the custom UserDetailsService to retrieve users and PasswordEncoder
     * to verify password hashes securely.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /**
     * Configures the HTTP security rules for the application.
     *
     * Defines:
     * - which endpoints are publicly accessible
     * - which endpoints require authentication
     * - stateless session management (JWT-based authentication)
     * - CSRF disabling for REST API usage
     * - registration of the JWT authentication filter in the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain
            (HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception{
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/drafts").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tags/**").permitAll()
                        .anyRequest().authenticated()
        )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
