package com.BFF_paye_ton_kawa.product;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.Utils.JsonSingleResponse;
import com.BFF_paye_ton_kawa.order.OrderController;
import com.BFF_paye_ton_kawa.product.DTO.ProductRequestDTO;
import com.BFF_paye_ton_kawa.product.DTO.ProductResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product API")
@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    private final ProductServices productServices;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/")
    public JsonMultipleResponse<ProductResponseDTO> getProducts() {


        JsonMultipleResponse<ProductResponseDTO> result = productServices.getAllProducts();
        logger.info("All products fetched successfully");
        return result;
    }

    @GetMapping("/{id}")

    public JsonSingleResponse<ProductResponseDTO> getProductById(@PathVariable @Size(min = 20, message = "ID not valid, it should contain 20 or more characters") String id) throws Exception {

        JsonSingleResponse<ProductResponseDTO> response = productServices.getProductById(id);
        logger.info("Product with ID: {} fetched successfully", id);
        return response;
    }

    @PostMapping("/")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productinformation) {
        return productServices.productCreation(productinformation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable @Size(min = 20, message = "ID not valid, it should contain 20 or more characters") String id) {
        productServices.deleteProduct(id);
        logger.info("Product with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

}

