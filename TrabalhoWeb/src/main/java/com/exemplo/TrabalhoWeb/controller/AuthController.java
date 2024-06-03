package com.exemplo.TrabalhoWeb.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.TrabalhoWeb.config.security.JwtTokenProvider;
import com.exemplo.TrabalhoWeb.entities.AuthRequest;
import com.exemplo.TrabalhoWeb.entities.AuthResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {
    // Verifica se as credenciais são válidas (admin / abc123)
    if ("admin".equals(loginRequest.getUsername()) && "abc123".equals(loginRequest.getPassword())) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          loginRequest.getUsername(),
          loginRequest.getPassword(),
          Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateToken(loginRequest.getUsername());

      return ResponseEntity.ok(new AuthResponse(token));
    } else {
      return ResponseEntity.status(401).body("Credenciais inválidas");
    }
  }

}
