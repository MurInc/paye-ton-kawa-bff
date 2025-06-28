package com.BFF_paye_ton_kawa.metrics;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestCountingFilter extends OncePerRequestFilter {
    private final MetricsService metrics;

    public RequestCountingFilter(MetricsService metrics) {
        this.metrics = metrics;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        metrics.httpRequestsCounter.increment();

        try {
            filterChain.doFilter(request, response);
        } finally {
            metrics.httpResponseCounter.increment();
        }
    }
}
