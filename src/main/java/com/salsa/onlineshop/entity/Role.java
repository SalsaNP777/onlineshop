package com.salsa.onlineshop.entity;

import com.salsa.onlineshop.utils.constant.Erole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder//for instance object
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private Erole role;
}