package com.bff_paye_ton_kawa.product;

import com.bff_paye_ton_kawa.product.DTO.ProductRequestDTO;
import com.bff_paye_ton_kawa.product.DTO.ProductResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product API")
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/")
    public List<ProductResponseDTO> getProducts() {
        return productServices.getAllProducts();
    }

    @GetMapping("/{id}")

    public ProductResponseDTO getProductById(@PathVariable @Size(min = 20, message = "ID not valid, it should contain 20 or more characters") String id) {
        return productServices.getProductById(id);
    }

    @PostMapping("/")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productinformation) {
        return productServices.productCreation(productinformation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable @Size(min = 20, message = "ID not valid, it should contain 20 or more characters") String id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

