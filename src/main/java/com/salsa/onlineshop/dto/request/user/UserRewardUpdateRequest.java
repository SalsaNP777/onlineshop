package com.salsa.onlineshop.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRewardUpdateRequest {
    private String id;
    private Integer totalReward;
}