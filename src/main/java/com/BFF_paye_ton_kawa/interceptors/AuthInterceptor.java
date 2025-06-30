package com.BFF_paye_ton_kawa.interceptors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
    public class AuthInterceptor implements HandlerInterceptor {

    private final List<String> allowedScopes = List.of("admin:client",
            "admin:loyalty",
            "admin:order",
            "admin:product",
            "user:client",
            "user:loyalty",
            "user:order",
            "user:product");
    private final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {;
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized");
                return false;
            }
            String token = authHeader.substring(7);

            try {
                System.out.println("user.dir  = " + System.getProperty("user.dir"));
                Path verifier = Paths.get("auth", "token-verifier").toAbsolutePath();
                System.out.println("looking at " + verifier + "  exists=" + Files.exists(verifier));
                String verifierPath = Paths.get("auth/token-verifier").toAbsolutePath().toString();
                if (!Paths.get(verifierPath).toFile().exists()) {
                    logger.error("Token verifier script not found at: {}", verifierPath);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Internal Server Error: Token verifier not found");
                    return false;
                }
                ProcessBuilder pb = new ProcessBuilder(verifierPath, authHeader);
                Process process = pb.start();
                int exitCode = process.waitFor();
                System.out.println("PreHandle: Token verified successfully.");
                if (exitCode != 0) {

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized: invalid token");
                    return false;
                }
                // Decode JWT payload
                String[] parts = token.split("\\.");
                if (parts.length != 3) throw new IllegalArgumentException("Malformed token");

                String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
                ObjectMapper mapper = new ObjectMapper();
                JsonNode payload = mapper.readTree(payloadJson);

                String scopeStr = payload.has("scope") ? payload.get("scope").asText() : "";
                List<String> tokenScopes = Arrays.asList(scopeStr.split(" "));

                boolean hasScope = allowedScopes.stream().anyMatch(tokenScopes::contains);

                if (!hasScope) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Forbidden: insufficient scope");
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
