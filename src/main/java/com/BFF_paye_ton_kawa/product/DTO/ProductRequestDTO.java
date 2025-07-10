package com.BFF_paye_ton_kawa.product.DTO;

import jakarta.validation.constraints.*;


public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotBlank(message = " origin is requiredProduct")
    private String origin;

    @NotNull(message = "Product price is required")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Double price;

    @PositiveOrZero(message = "Stock must be positive or zero")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
