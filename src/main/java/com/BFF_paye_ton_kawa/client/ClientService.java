package com.BFF_paye_ton_kawa.client;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.client.DTO.ClientRequestDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientResponseDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientsResponseDTO;
import com.BFF_paye_ton_kawa.config.ApiProperties;
import com.BFF_paye_ton_kawa.product.DTO.ProductResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClientService {
    final RestTemplate restTemplate;
    final ApiProperties apiProps;

    public ClientService(RestTemplate restTemplate, ApiProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiProps = apiProps;
    }

    public JsonMultipleResponse<ClientResponseDTO> getAllClients(int page, int limit) {
        String basePath = apiProps.getClientUrl();
        String path = UriComponentsBuilder.fromUriString(basePath).queryParam("page", page).queryParam("limit", limit).toUriString();
        return restTemplate.exchange(path, HttpMethod.GET, null , new ParameterizedTypeReference<JsonMultipleResponse<ClientResponseDTO>>(){}).getBody();

    }

    public ClientResponseDTO getClientById(String id) {
        String path = apiProps.getClientUrl() + "/" + id;
        return restTemplate.getForObject(path, ClientResponseDTO.class);
    }

    public ClientResponseDTO ClientCreation(ClientRequestDTO clientInformation) {
        String path = apiProps.getClientUrl();
        return restTemplate.postForObject(path, clientInformation, ClientResponseDTO.class);
    }

    public void deleteClient(String id) {

        String path = UriComponentsBuilder.fromUriString(apiProps.getClientUrl())
                .pathSegment(id)
                .toUriString();
        System.out.println("Deleting client at path: " + path);
        restTemplate.delete(path);
    }
}
