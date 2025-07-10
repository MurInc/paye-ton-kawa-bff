package com.BFF_paye_ton_kawa.config;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "management.otlp.tracing")
public class MetricsProperties {
    @NotBlank(message = "Metrics URL must not be blank")
    @URL(message = "Metrics API URL must be a valid URL")
    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
