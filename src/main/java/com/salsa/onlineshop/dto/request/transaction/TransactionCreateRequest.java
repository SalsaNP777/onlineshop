package com.salsa.onlineshop.dto.request.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionCreateRequest {
    private Integer amount;
    private String merchantId;
    private String rewardId;
    private String userId;
    private String productId;
}
