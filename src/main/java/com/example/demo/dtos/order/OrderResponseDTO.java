package com.example.demo.dtos.order;

import com.example.demo.models.OrderAddress;
import com.example.demo.models.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private List<OrderItemResponseDTO> orderItems;
    private OrderAddress orderAddress;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
