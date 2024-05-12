package com.salsa.onlineshop.dto.response.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private Integer price;
    private Integer stock;
    private String merchantId;
}
