package com.bff_paye_ton_kawa.order.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderRequestDTO {

    @NotBlank(message = "User ID is required")
    private String idUser;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Address is required")
    private String address;

    @Size(min = 1, message = "At least one product is required")
    private List<Product> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
