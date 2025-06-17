package com.bff_paye_ton_kawa.order.DTO;

import java.util.List;

public class OrderResponseDTO {
    private String id;
    private String idUser;
    private String timestamp;
    private  String status;
    private String address;
    private List<Product> products;

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public List<Product> getProducts() {
        return products;
    }
}
