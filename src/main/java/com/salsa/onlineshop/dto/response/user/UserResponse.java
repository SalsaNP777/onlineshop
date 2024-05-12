package com.salsa.onlineshop.dto.response.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String password;
    private Integer totalReward;
}
