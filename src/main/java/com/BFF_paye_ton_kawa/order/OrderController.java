package com.BFF_paye_ton_kawa.order;

import com.BFF_paye_ton_kawa.Utils.JsonMultipleResponse;
import com.BFF_paye_ton_kawa.Utils.JsonSingleResponse;
import com.BFF_paye_ton_kawa.order.DTO.OrderRequestDTO;
import com.BFF_paye_ton_kawa.order.DTO.OrderResponseDTO;
import io.swagger.v3.core.util.Json;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public JsonMultipleResponse<OrderResponseDTO> getOrders() {
        return orderService.getAllOrders();
    };

    @GetMapping("/{id}")
    public JsonSingleResponse<OrderResponseDTO> getOrderById(@RequestParam String id) throws Exception {

            return orderService.getOrder(id);

    };

    @PostMapping("/")
    public JsonSingleResponse<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.orderCreation(orderRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
       orderService.deleteOrder(id);
       return ResponseEntity.noContent().build();
    }
}
