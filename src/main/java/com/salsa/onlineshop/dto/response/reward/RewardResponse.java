package com.salsa.onlineshop.dto.response.reward;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RewardResponse {
    private String id;
    private String name;
    private Integer point;
}
