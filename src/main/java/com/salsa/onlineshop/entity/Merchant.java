package com.salsa.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "m_merchant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String merchantName;
    @OneToMany(mappedBy = "id")
    private List<Product> products;
    @OneToMany (mappedBy = "id")
    private List<Transaction> transactions;
//    @Column(name = "role_id")
//    private String roleId;
}
