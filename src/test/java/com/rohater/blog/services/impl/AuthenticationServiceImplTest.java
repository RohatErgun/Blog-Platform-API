package com.rohater.blog.services.impl;

import com.rohater.blog.domain.model.User;
import com.rohater.blog.repository.UserRepository;
import com.rohater.blog.security.BlogUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    void authenticate_shouldReturnUserDetails_whenPasswordMatches(){
        String email = "test@mail.com";
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword";

        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .build();

        UserDetails userDetails = new BlogUserDetails(user);

        when(userDetailsService.loadUserByUsername(email))
                .thenReturn(userDetails);

        when(passwordEncoder.matches(rawPassword, encodedPassword))
                .thenReturn(true);

        UserDetails result = authenticationService.authenticate(email, rawPassword);

        assertNotNull(result);
        assertEquals(email, result.getUsername());
    }

    @Test
    void authenticate_shouldThrowException_whenPasswordInvalid() {

        String email = "test@mail.com";
        String rawPassword = "wrongPassword";
        String encodedPassword = "encodedPassword";

        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .build();

        UserDetails userDetails = new BlogUserDetails(user);

        when(userDetailsService.loadUserByUsername(email))
                .thenReturn(userDetails);

        when(passwordEncoder.matches(rawPassword, encodedPassword))
                .thenReturn(false);

        assertThrows(
                BadCredentialsException.class,
                () -> authenticationService.authenticate(email, rawPassword)
        );
    }

}
