package com.BFF_paye_ton_kawa.client;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.Utils.JsonSingleResponse;
import com.BFF_paye_ton_kawa.client.DTO.ClientRequestDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientResponseDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientsResponseDTO;
import com.BFF_paye_ton_kawa.config.ApiProperties;
import com.BFF_paye_ton_kawa.order.OrderController;
import com.BFF_paye_ton_kawa.product.DTO.ProductResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientService {
    final RestTemplate restTemplate;
    final ApiProperties apiProps;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    public ClientService(RestTemplate restTemplate, ApiProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiProps = apiProps;
    }

    public JsonMultipleResponse<ClientResponseDTO> getAllClients(int page, int limit) {
        String basePath = apiProps.getClientUrl();
        String path = UriComponentsBuilder.fromUriString(basePath).queryParam("page", page).queryParam("limit", limit).toUriString();
        logger.info("Fetching clients from path: {}", path);
        return restTemplate.exchange(path, HttpMethod.GET, null , new ParameterizedTypeReference<JsonMultipleResponse<ClientResponseDTO>>(){}).getBody();

    }

    public JsonSingleResponse<ClientResponseDTO> getClientById(String id) {
        String path = apiProps.getClientUrl() + "/" + id;
        logger.info("Fetching client with ID: {} from path: {}", id, path);
        return restTemplate.exchange(path, HttpMethod.GET, null , new ParameterizedTypeReference<JsonSingleResponse<ClientResponseDTO>>(){}).getBody();
    }

    public ClientResponseDTO ClientCreation(ClientRequestDTO clientInformation) {
        String path = apiProps.getClientUrl();
        logger.info("Creating client at path: {}", path);
        return restTemplate.postForObject(path, clientInformation, ClientResponseDTO.class);
    }

    public void deleteClient(String id) {
        String path = UriComponentsBuilder.fromUriString(apiProps.getClientUrl())
                .pathSegment(id)
                .toUriString();
        logger.info("Deleting client with ID: {} at path: {}", id, path);
        restTemplate.delete(path);
    }
}
