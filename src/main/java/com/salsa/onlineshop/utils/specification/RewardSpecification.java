package com.salsa.onlineshop.utils.specification;

import com.salsa.onlineshop.dto.request.reward.RewardSearchRequest;
import com.salsa.onlineshop.entity.Reward;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RewardSpecification {
    public static Specification<Reward> getSpecification(RewardSearchRequest request){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%"));
            }
            if (request.getPoint()!=null){
                predicates.add(criteriaBuilder.equal(root.get("point"), request.getPoint()));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
