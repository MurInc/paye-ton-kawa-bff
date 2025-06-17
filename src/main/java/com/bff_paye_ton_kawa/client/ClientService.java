package com.bff_paye_ton_kawa.client;

import com.bff_paye_ton_kawa.client.dto.ClientRequestDTO;
import com.bff_paye_ton_kawa.client.dto.ClientResponseDTO;
import com.bff_paye_ton_kawa.client.dto.ClientsResponseDTO;
import com.bff_paye_ton_kawa.config.ApiProperties;
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

    public ClientsResponseDTO getAllClients(int page, int limit) {
        String basePath = apiProps.getClientUrl();
        String path = UriComponentsBuilder.fromUriString(basePath).queryParam("page", page).queryParam("limit", limit).toUriString();
        return restTemplate.getForObject(path, ClientsResponseDTO.class);
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
