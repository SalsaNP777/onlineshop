package com.salsa.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "total_price")
    private Integer totalPrice;
    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward rewardId;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchantId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
}
