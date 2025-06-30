package com.BFF_paye_ton_kawa.client;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.Utils.JsonSingleResponse;
import com.BFF_paye_ton_kawa.client.DTO.ClientRequestDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientResponseDTO;
import com.BFF_paye_ton_kawa.client.DTO.ClientsResponseDTO;
import com.BFF_paye_ton_kawa.order.OrderController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "Client API")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public JsonMultipleResponse<ClientResponseDTO> getClients(@RequestParam Integer page, @RequestParam Integer limit) {
        return clientService.getAllClients(page, limit);
    }

    @GetMapping("/{id}")
    public JsonSingleResponse<ClientResponseDTO> getClient(@PathVariable String id) {
        return  clientService.getClientById(id);
    }

    @PostMapping("/")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO clientInformation) {
        return clientService.ClientCreation(clientInformation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID", description = "Deletes a client from the system using their unique identifier.")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
