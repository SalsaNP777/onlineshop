package com.salsa.onlineshop.utils.specification;

import com.salsa.onlineshop.dto.request.transaction.TransactionSearchRequest;
import com.salsa.onlineshop.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {
    public static Specification<Transaction> getSpecification(TransactionSearchRequest request) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getUserId() != null) {
                predicates.add(criteriaBuilder.like(root.get("userId"), "%" + request.getUserId() + "%"));
            }
            if (request.getMerchantId() != null) {
                predicates.add(criteriaBuilder.like(root.get("merchantId"), "%" + request.getMerchantId() + "%"));
            }
            if (request.getRewardId() != null) {
                predicates.add(criteriaBuilder.like(root.get("rewardId"), "%" + request.getRewardId() + "%"));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
