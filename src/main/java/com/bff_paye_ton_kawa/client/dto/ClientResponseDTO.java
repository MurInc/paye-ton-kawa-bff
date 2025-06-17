package com.bff_paye_ton_kawa.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponseDTO {
    @JsonProperty("_id")
    private String id;
    private String email;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
