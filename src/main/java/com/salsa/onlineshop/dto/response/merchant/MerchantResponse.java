package com.salsa.onlineshop.dto.response.merchant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantResponse {
    private String id;
    private String name;
}
