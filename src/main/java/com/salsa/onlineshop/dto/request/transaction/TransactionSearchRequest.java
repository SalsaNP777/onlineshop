package com.salsa.onlineshop.dto.request.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionSearchRequest {
    private String id;
    private String merchantId;
    private String rewardId;
    private String userId;
}
