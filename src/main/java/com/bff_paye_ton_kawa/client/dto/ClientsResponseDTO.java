package com.bff_paye_ton_kawa.client.dto;

import java.util.List;

public class ClientsResponseDTO {
    private int page;
    private int totalPages;
    private int totalUsers;

    public List<ClientResponseDTO> users;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public List<ClientResponseDTO> getUsers() {
        return users;
    }

    public void setUsers(List<ClientResponseDTO> users) {
        this.users = users;
    }
}
