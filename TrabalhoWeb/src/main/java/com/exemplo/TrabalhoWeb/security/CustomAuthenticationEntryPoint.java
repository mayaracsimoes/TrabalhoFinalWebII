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
        // Verifica se o código de status da resposta é 403
        if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
            // Se for 403, envia o erro 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            // Caso contrário, continua com o fluxo normal e deixa o contêiner lidar com o erro
            throw authException;
        }
    }
}

