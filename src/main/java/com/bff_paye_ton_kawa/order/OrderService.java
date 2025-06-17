package com.bff_paye_ton_kawa.order;

import com.bff_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.bff_paye_ton_kawa.Utils.JsonSingleResponse;
import com.bff_paye_ton_kawa.config.ApiProperties;
import com.bff_paye_ton_kawa.order.DTO.OrderRequestDTO;
import com.bff_paye_ton_kawa.order.DTO.OrderResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    ApiProperties apiPros;

    public OrderService(RestTemplate restTemplate, ApiProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiPros = apiProps;
    }
    public JsonMultipleResponse<OrderResponseDTO> getAllOrders() {
        String url = apiPros.getOrderUrl();
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<JsonMultipleResponse<OrderResponseDTO>>() {
        }).getBody();
    }

    public JsonSingleResponse<OrderResponseDTO> getOrder(String id) {
        String url = UriComponentsBuilder.fromUriString(apiPros.getOrderUrl())
                .pathSegment(id)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<JsonSingleResponse<OrderResponseDTO>>() {
        }).getBody();
    }

    public JsonSingleResponse<OrderResponseDTO> orderCreation(OrderRequestDTO orderInformation) {
        String url = apiPros.getOrderUrl();
        HttpEntity<OrderRequestDTO> requestEntity = new HttpEntity<>(orderInformation);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<JsonSingleResponse<OrderResponseDTO>>() {
        }).getBody();
    }

    public void deleteOrder(String id) {
        String url = UriComponentsBuilder.fromUriString(apiPros.getOrderUrl()).pathSegment(id).toUriString();
        restTemplate.delete(url);
    }
}
