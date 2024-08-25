package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.models.payment.PaymentDetails;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {  
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "order")
    private List<OrderItem> orderItemList;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_order_user_id")
    private User userOrder;

    @Embedded
    private OrderAddress orderAddress;
    @OneToOne
    @JoinColumn(name="payment_details_id")
    private PaymentDetails paymentDetails;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt= LocalDateTime.now();

   @Enumerated(EnumType.STRING)
   private OrderStatus status;








}
