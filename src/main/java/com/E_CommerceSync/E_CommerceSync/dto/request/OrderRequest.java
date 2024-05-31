package com.E_CommerceSync.E_CommerceSync.dto.request;

import com.E_CommerceSync.E_CommerceSync.utils.helper.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrdersTable")
public class OrderRequest {

    private Long Id;
    private Long customerId;
    private LocalDateTime orderDate = LocalDateTime.now();
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}