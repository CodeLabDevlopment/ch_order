package com.coffeehub.order_ms.infrastructure.db.repository;

import com.coffeehub.order_ms.infrastructure.db.entity.StatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistoryEntity, UUID> {

}
