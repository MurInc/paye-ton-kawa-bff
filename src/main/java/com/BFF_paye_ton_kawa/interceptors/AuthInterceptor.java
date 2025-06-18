package com.BFF_paye_ton_kawa.interceptors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
    public class AuthInterceptor implements HandlerInterceptor {

    private final List<String> allowedScopes = List.of("read", "write");
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
                String verifierPath = Paths.get("auth/win-token-verifier(1).exe").toAbsolutePath().toString();
                ProcessBuilder pb = new ProcessBuilder(verifierPath, authHeader);
                Process process = pb.start();
                int exitCode = process.waitFor();
                System.out.println("PreHandle: Token verified successfully.");
                if (exitCode != 0) {
                    System.out.println("PreHandle: TCOUCOU.");

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
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                response.getWriter().write("Malformed token");
                return true;
            }
        }
    }
