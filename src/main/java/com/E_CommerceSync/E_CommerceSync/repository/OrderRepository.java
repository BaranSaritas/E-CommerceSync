package com.E_CommerceSync.E_CommerceSync.repository;

import com.E_CommerceSync.E_CommerceSync.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
