package com.rohater.blog.security;


import com.rohater.blog.domain.model.User;
import com.rohater.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found " + email)
        );

        return new BlogUserDetails(user);
    }
}
