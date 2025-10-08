package com.oauth2.springwithmongodb.security;

import com.oauth2.springwithmongodb.entity.User;
import com.oauth2.springwithmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // ✅ Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // ✅ Return Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),             // username
                user.getPassword(),          // password
                getAuthorities(user)         // authorities (roles)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // ✅ যদি role না থাকে, default "ROLE_USER" ধরব
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
