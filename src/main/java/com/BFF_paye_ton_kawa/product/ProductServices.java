package com.BFF_paye_ton_kawa.product;

import com.BFF_paye_ton_kawa.config.ApiProperties;
import com.BFF_paye_ton_kawa.product.DTO.ProductRequestDTO;
import com.BFF_paye_ton_kawa.product.DTO.ProductResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ProductServices {
    private final RestTemplate restTemplate;

    ApiProperties apiPros;


    public ProductServices(RestTemplate restTemplate, ApiProperties apiProps) {
        this.restTemplate = restTemplate;
        this.apiPros = apiProps;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return restTemplate.exchange(apiPros.getProductUrl(), HttpMethod.GET, null , new ParameterizedTypeReference<List<ProductResponseDTO>>(){}).getBody();
    }

    public ProductResponseDTO getProductById(String id) {
        String path = apiPros.getProductUrl() + "/" + id;
        return restTemplate.getForObject(path, ProductResponseDTO.class);
    }

    public ProductResponseDTO productCreation(ProductRequestDTO productInformation) {
        String path = apiPros.getProductUrl();
        return restTemplate.postForObject(path, productInformation, ProductResponseDTO.class);
    }

    public void deleteProduct(String id) {
        String path = UriComponentsBuilder.fromUriString(apiPros.getProductUrl())
                .pathSegment(id)
                .toUriString();
        restTemplate.delete(path);
    }
}
