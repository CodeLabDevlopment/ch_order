package com.coffeehub.order_ms.infrastructure.db.repository;

import com.coffeehub.order_ms.infrastructure.db.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

}
