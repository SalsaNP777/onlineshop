package com.salsa.onlineshop.dto.request.reward;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RewardUpdateRequest {
    private String id;
    private String name;
    private Integer point;
}
