package com.exemplo.TrabalhoWeb.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // Obtendo o status code da resposta
        int statusCode = response.getStatus();
        System.out.println("Status code da resposta: " + statusCode);

        // Verifica se o status code recebido é diferente de 200 (OK)
        if (statusCode != HttpServletResponse.SC_OK) {
            // Retorna o status code recebido
            response.sendError(statusCode);
        } else {
            // Se for 200 (OK), retorna Unauthorized (401)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}

