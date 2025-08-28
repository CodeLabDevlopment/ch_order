package com.coffeehub.order_ms.infrastructure.db.entity;

import com.coffeehub.order_ms.domain.enums.OrderStatus;
import com.coffeehub.order_ms.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_ch_order_status_history")
@Table(name = "t_ch_order_status_history")
public class StatusHistoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JsonBackReference
    private Order order;

    private LocalDateTime changedAt;

}
