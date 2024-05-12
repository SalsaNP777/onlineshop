package com.salsa.onlineshop.dto.request.merchant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantCreateRequest {
    private String name;
}
