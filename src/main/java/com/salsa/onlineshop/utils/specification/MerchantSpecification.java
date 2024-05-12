package com.salsa.onlineshop.utils.specification;

import com.salsa.onlineshop.dto.request.merchant.MerchantSearchRequest;
import com.salsa.onlineshop.entity.Merchant;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MerchantSpecification {
    public static Specification<Merchant> getSpecification(MerchantSearchRequest request) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}