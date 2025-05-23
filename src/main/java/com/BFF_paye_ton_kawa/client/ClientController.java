package com.BFF_paye_ton_kawa.client;

import com.BFF_paye_ton_kawa.client.DTO.ClientResponseDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientsResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ClientsResponseDTO getClients(@RequestParam Integer page, @RequestParam Integer limit) {
        return clientService.getAllClients(page, limit);
    }

    @GetMapping("/{id}")
    public ClientResponseDTO getClient(@PathVariable String id) {
        return  clientService.getClientById(id);
    }
}
