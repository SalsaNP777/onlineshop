package com.salsa.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String productName;
    @Column(name = "price")
    private Integer productPrice;
    @Column(name = "stock")
    private Integer productStock;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchantId;
}
