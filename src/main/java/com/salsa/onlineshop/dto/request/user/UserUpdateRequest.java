package com.salsa.onlineshop.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    private String id;
    private String name;
    private String email;
    private String password;
}
