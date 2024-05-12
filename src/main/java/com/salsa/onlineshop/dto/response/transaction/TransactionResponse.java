package com.salsa.onlineshop.dto.response.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {
    private String id;
    private Integer amount;
    private  Integer totalPrice;
    private String merchantId;
    private String rewardId;
    private String userId;
    private String productId;
}
