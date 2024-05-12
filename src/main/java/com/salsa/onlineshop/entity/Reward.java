package com.salsa.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_reward")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String rewardName;
    @Column(name = "point")
    private Integer rewardPoint;
//    @OneToOne(mappedBy = "reward_id")
//    private Transaction transactionId;
}
