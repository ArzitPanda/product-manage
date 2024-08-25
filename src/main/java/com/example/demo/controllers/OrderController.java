package com.example.demo.controllers;


import com.example.demo.dtos.order.OrderRequestDTO;
import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.OrderStatus;
import com.example.demo.services.order.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        OrderResponseDTO orderResponseDTO = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
    }
}
