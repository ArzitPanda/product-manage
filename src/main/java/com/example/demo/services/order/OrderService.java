package com.example.demo.services.order;

import com.example.demo.dtos.order.OrderRequestDTO;
import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus newStatus);


    public OrderResponseDTO CancelOrder(Long orderId);

    List<OrderResponseDTO> getAllOrdersByUserId(Long userId, Pageable pageable);
    List<OrderResponseDTO> getAllOrders(Pageable pageable);


}


