package com.salsa.onlineshop.dto.request.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductUpdateRequest {
    private String id;
    private String name;
    private Integer price;
    private Integer stock;
}
