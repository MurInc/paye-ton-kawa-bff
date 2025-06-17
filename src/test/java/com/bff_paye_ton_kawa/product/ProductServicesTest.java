//package com.bff_paye_ton_kawa.product;
//
//import com.bff_paye_ton_kawa.config.ApiProperties;
//import com.bff_paye_ton_kawa.product.dto.ProductRequestDTO;
//import com.bff_paye_ton_kawa.product.dto.ProductResponseDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ProductServicesTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ApiProperties apiProperties;
//
//    @InjectMocks
//    private ProductServices productServices;
//
//    @BeforeEach
//    void setUp() {
//        when(apiProperties.getProductUrl()).thenReturn("http://test-api/products");
//    }
//
//    @Test
//    void getAllProductsReturnsListOfProducts() {
//        List<ProductResponseDTO> expectedProducts = Arrays.asList(
//                new ProductResponseDTO("1", "Coffee", "10.00"),
//                new ProductResponseDTO("2", "Tea", "5.00")
//        );
//
//        when(restTemplate.exchange(
//                eq("http://test-api/products"),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(ResponseEntity.ok(expectedProducts));
//
//        List<ProductResponseDTO> actualProducts = productServices.getAllProducts();
//
//        assertEquals(expectedProducts, actualProducts);
//    }
//
//    @Test
//    void getProductByIdReturnsProduct() {
//        ProductResponseDTO expectedProduct = new ProductResponseDTO("1", "Coffee", "10.00");
//
//        when(restTemplate.getForObject(
//                "http://test-api/products/1",
//                ProductResponseDTO.class
//        )).thenReturn(expectedProduct);
//
//        ProductResponseDTO actualProduct = productServices.getProductById("1");
//
//        assertEquals(expectedProduct, actualProduct);
//    }
//
//    @Test
//    void productCreationReturnsCreatedProduct() {
//        ProductRequestDTO request = new ProductRequestDTO("Coffee", "10.00");
//        ProductResponseDTO expectedProduct = new ProductResponseDTO("1", "Coffee", "10.00");
//
//        when(restTemplate.postForObject(
//                "http://test-api/products",
//                request,
//                ProductResponseDTO.class
//        )).thenReturn(expectedProduct);
//
//        ProductResponseDTO actualProduct = productServices.productCreation(request);
//
//        assertEquals(expectedProduct, actualProduct);
//    }
//
//    @Test
//    void deleteProductCallsDeleteEndpoint() {
//        productServices.deleteProduct("1");
//
//        verify(restTemplate).delete("http://test-api/products/1");
//    }
//
//    @Test
//    void getAllProductsWhenApiReturnsEmptyList() {
//        when(restTemplate.exchange(
//                anyString(),
//                eq(HttpMethod.GET),
//                isNull(),
//                any(ParameterizedTypeReference.class)
//        )).thenReturn(ResponseEntity.ok(Collections.emptyList()));
//
//        List<ProductResponseDTO> products = productServices.getAllProducts();
//
//        assertTrue(products.isEmpty());
//    }
//
//    @Test
//    void getProductByIdWhenProductNotFound() {
//        when(restTemplate.getForObject(
//                anyString(),
//                eq(ProductResponseDTO.class)
//        )).thenReturn(null);
//
//        ProductResponseDTO product = productServices.getProductById("999");
//
//        assertNull(product);
//    }
//}