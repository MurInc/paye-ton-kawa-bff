package com.BFF_paye_ton_kawa.product;

import com.BFF_paye_ton_kawa.config.ApiProperties;
import com.BFF_paye_ton_kawa.product.DTO.ProductResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        System.out.println("getAllProducts");
        System.out.println(apiPros.getProductUrl());
        List<ProductResponseDTO> products =
                restTemplate.exchange(apiPros.getProductUrl(), HttpMethod.GET, null , new ParameterizedTypeReference<List<ProductResponseDTO>>(){}).getBody();
        System.out.println(products);
        return products;
    }

    public ProductResponseDTO getProductById(String id) {
        String path = apiPros.getProductUrl() + "/" + id;
        ProductResponseDTO product = restTemplate.getForObject(path, ProductResponseDTO.class);
        System.out.println(product);
        return product;
    }
}
