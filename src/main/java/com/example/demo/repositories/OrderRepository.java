package com.example.demo.repositories;


import com.example.demo.models.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrder_IdOrderByUpdatedAtAsc(Long id, Pageable pageable);
}