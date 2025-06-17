package com.bff_paye_ton_kawa.client.dto;

import jakarta.validation.constraints.*;

public class ClientRequestDTO {

    @NotBlank()
    private String first_name;

    @NotBlank()
    private String last_name;

    @NotBlank()
    @Email()
    private String email;

    @NotNull()
    @PositiveOrZero()
    @Size(min = 2)
    private int age;

    @NotBlank()
    private   String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
