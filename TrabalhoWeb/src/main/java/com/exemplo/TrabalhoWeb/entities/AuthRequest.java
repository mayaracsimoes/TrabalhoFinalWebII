package com.exemplo.TrabalhoWeb.entities;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Data;

@Data
public class AuthRequest implements UserDetailsService {

    private String username;
    private String password;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    // Getters e setters
}
