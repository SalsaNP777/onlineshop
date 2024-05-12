package com.salsa.onlineshop.dto.request.reward;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RewardCreateRequest {
    private String name;
    private Integer point;
}
