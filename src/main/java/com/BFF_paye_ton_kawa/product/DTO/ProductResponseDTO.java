package com.BFF_paye_ton_kawa.product.DTO;

public class ProductResponseDTO {

    public String name;
    public String description;
    public String origin;
    public double price;
    public int stock;

    public String id;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


}
