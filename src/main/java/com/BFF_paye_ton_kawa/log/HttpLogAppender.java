package com.BFF_paye_ton_kawa.log;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.BFF_paye_ton_kawa.order.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpLogAppender extends AppenderBase<ILoggingEvent> {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> payload = new HashMap<>();
            payload.put("Timestamp", new Date(eventObject.getTimeStamp()));
            payload.put("Level", eventObject.getLevel().toString());
            payload.put("Logger", eventObject.getLoggerName());
            payload.put("Thread", eventObject.getThreadName());
            payload.put("Message", eventObject.getFormattedMessage());
            payload.put("Service", "BFF_paye_ton_kawa");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            logger.error("Failed to send log to HTTP endpoint: {}", url, e);
        }
    }
}