package com.coffeehub.order_ms.infrastructure.db.entity;

import com.coffeehub.order_ms.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_ch_order_items")
@Table(name = "t_ch_order_items")
public class OrderItemEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JsonBackReference
    private Order order;
    private UUID productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;

}
