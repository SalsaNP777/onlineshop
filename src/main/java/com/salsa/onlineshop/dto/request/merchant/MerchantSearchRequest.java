package com.salsa.onlineshop.dto.request.merchant;

import com.salsa.onlineshop.entity.Product;
import com.salsa.onlineshop.entity.Transaction;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantSearchRequest {
    private String id;
    private String name;
    private List<Product> products;
    private List<Transaction> transactions;
}