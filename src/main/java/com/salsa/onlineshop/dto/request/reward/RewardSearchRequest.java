package com.salsa.onlineshop.dto.request.reward;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RewardSearchRequest {
    private String id;
    private String name;
    private Integer point;
}
