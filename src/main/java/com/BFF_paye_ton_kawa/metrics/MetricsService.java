package com.BFF_paye_ton_kawa.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;

@Component
public class MetricsService {
    public final Counter httpRequestsCounter;
    public final Counter httpResponseCounter;
    public final Counter httpErrorCounter;

    public MetricsService(MeterRegistry registry) {
        this.httpRequestsCounter = Counter.builder("http_requests_count_total")
                .description("Total number of HTTP requests")
                .register(registry);

        this.httpResponseCounter = Counter.builder("http_responses_count_total")
                .description("Total number of HTTP responses")
                .register(registry);

        this.httpErrorCounter = Counter.builder("http_errors_count_total")
                .description("Total number of HTTP errors")
                .register(registry);
    }
}
