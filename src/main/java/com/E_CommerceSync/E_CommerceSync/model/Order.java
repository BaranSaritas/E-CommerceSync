package com.E_CommerceSync.E_CommerceSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrdersTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long customerId;
    private LocalDateTime orderDate = LocalDateTime.now();
    private BigDecimal totalAmount;
    private String status;
}
