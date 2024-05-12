package com.salsa.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String userName;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "password")
    private String userPassword;
    @Column(name = "total_reward", columnDefinition = "integer default 0")
    private Integer totalReward;
    @OneToMany(mappedBy = "id")
    private List<Transaction> transactionId;
//    @Column(name = "role_id")
//    private String roleId;
}
