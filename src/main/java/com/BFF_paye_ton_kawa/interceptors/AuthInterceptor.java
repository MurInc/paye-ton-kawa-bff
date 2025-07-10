package com.BFF_paye_ton_kawa.interceptors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
    public class AuthInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null ) {;
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization header is missing");
                return false;
            }

            if (!authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not valid Bearer token");
                return false;
            }
            String token = authHeader.substring(7);

            try {
                Path verifier = Paths.get("auth", "token-verifier").toAbsolutePath();
                String verifierPath = Paths.get("auth/token-verifier").toAbsolutePath().toString();
                if (!Paths.get(verifierPath).toFile().exists()) {
                    logger.error("Token verifier script not found at: {}", verifierPath);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Token verifier script not found");
                    return false;
                }
                ProcessBuilder pb = new ProcessBuilder(verifierPath, authHeader);
                Process process = pb.start();
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return false;
                }
                // Decode JWT payload
                String[] parts = token.split("\\.");
                if (parts.length != 3) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed token");
                    return false;
                }
                return true;

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Malformed token");
                logger.error("Error processing token: {}", e.getMessage(), e);
                return false;
            }
        }
    }
