package com.BFF_paye_ton_kawa.order;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.Utils.JsonSingleResponse;
import com.BFF_paye_ton_kawa.config.ApiProperties;
import com.BFF_paye_ton_kawa.order.DTO.OrderRequestDTO;
import com.BFF_paye_ton_kawa.order.DTO.OrderResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
@Service
public class OrderService {
    private final RestTemplate restTemplate;

    ApiProperties apiPros;
    private static final Logger log = LogManager.getLogger(Controller.class);


    public OrderService(RestTemplate restTemplate, ApiProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiPros = apiProps;
    }
    public JsonMultipleResponse<OrderResponseDTO> getAllOrders() {
        String url = apiPros.getOrderUrl();
        JsonMultipleResponse<OrderResponseDTO> response =  restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<JsonMultipleResponse<OrderResponseDTO>>() {
        }).getBody();
        log.info("Fetching all orders from URL: {}", url);
        return response;
    }

    public JsonSingleResponse<OrderResponseDTO> getOrder(String id) {
        String url = UriComponentsBuilder.fromUriString(apiPros.getOrderUrl())
                .pathSegment(id)
                .toUriString();
        log.info("Fetching order with ID: {} from URL: {}", id, url);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<JsonSingleResponse<OrderResponseDTO>>() {
        }).getBody();
    }

    public JsonSingleResponse<OrderResponseDTO> orderCreation(OrderRequestDTO orderInformation) {
        String url = apiPros.getOrderUrl();
        HttpEntity<OrderRequestDTO> requestEntity = new HttpEntity<>(orderInformation);
        log.info("Creating order at URL: {}", url);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<JsonSingleResponse<OrderResponseDTO>>() {
        }).getBody();
    }

    public void deleteOrder(String id) {
        String url = UriComponentsBuilder.fromUriString(apiPros.getOrderUrl()).pathSegment(id).toUriString();
        log.info("Deleting order with ID: {} at URL: {}", id, url);
        restTemplate.delete(url);
    }
}
