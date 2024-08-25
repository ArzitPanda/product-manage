package com.example.demo.services.order;

import com.example.demo.repositories.*;
import com.example.demo.dtos.order.OrderItemResponseDTO;
import com.example.demo.dtos.order.OrderRequestDTO;
import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.*;
import com.example.demo.models.payment.PaymentDetails;
import com.example.demo.repositories.payments.PaymentDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Cart cart = cartRepository.findById(orderRequestDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity((long) cartItem.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());
        PaymentDetails paymentDetails = paymentDetailsRepository.findById(orderRequestDTO.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment details not found"));
        Address address = addressRepository.findById(orderRequestDTO.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setStreet(address.getStreet());
        orderAddress.setCity(address.getCity());
        orderAddress.setState(address.getState());
        orderAddress.setZipCode(address.getZipCode());
        orderAddress.setCountry(address.getCountry());
        orderAddress.setPhoneNumber(address.getPhoneNumber());

        Order order = new Order();
        order.setOrderItemList(orderItems);
        order.setPaymentDetails(paymentDetails);
        order.setOrderAddress(orderAddress);
        order.setStatus(OrderStatus.ORDERED);

        order = orderRepository.save(order);

        cart.getCartItems().clear();
       cartItemRepository.deleteAll(cart.getCartItems());
        cart.setCartItems(new ArrayList<>());
        cart.setTotal(0.0);
        cartRepository.save(cart);

        return convertToResponseDTO(order);
    }

    @Transactional
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


            order.setStatus(OrderStatus.CANCELLED);
        order = orderRepository.save(order);

        return convertToResponseDTO(order);
    }

    @Override
    public OrderResponseDTO CancelOrder(Long orderId) {
        return updateOrderStatus(orderId,OrderStatus.CANCELLED);
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));

      return   orderRepository.findByUserOrder_IdOrderByUpdatedAtAsc(userId,pageable).stream().map((ele)->convertToResponseDTO(ele)).collect(Collectors.toList());

    }
    @Override
    public List<OrderResponseDTO> getAllOrders(Pageable pageable) {
        return   orderRepository.findAll(pageable).stream().map((ele)->convertToResponseDTO(ele)).collect(Collectors.toList());
    }
    private OrderResponseDTO convertToResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setOrderAddress(order.getOrderAddress());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setCreatedAt(order.getCreatedAt());
        orderResponseDTO.setUpdatedAt(order.getUpdatedAt());
        List<OrderItemResponseDTO> orderItemResponseDTOs = order.getOrderItemList().stream()
                .map(orderItem -> {
                    OrderItemResponseDTO dto = new OrderItemResponseDTO();
                    dto.setProductId(orderItem.getProduct().getId());
                    dto.setProductName(orderItem.getProduct().getName());
                    dto.setQuantity(orderItem.getQuantity());
                    return dto;
                }).collect(Collectors.toList());

        orderResponseDTO.setOrderItems(orderItemResponseDTOs);
        return orderResponseDTO;
    }

}
