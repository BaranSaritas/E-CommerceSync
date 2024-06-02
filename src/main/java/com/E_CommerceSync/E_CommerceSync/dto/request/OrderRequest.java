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
public class OrderRequest {
    private Long customerId;
    private BigDecimal totalAmount;
}