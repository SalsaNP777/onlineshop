package com.salsa.onlineshop.utils.specification;

import com.salsa.onlineshop.dto.request.product.ProductSearchRequest;
import com.salsa.onlineshop.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(ProductSearchRequest request){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+request.getName()+"%"));
            }
            if (request.getPrice()!=null){
                predicates.add(criteriaBuilder.equal(root.get("price"), request.getPrice()));
            }
            if (request.getStock()!=null){
                predicates.add(criteriaBuilder.equal(root.get("stock"), request.getStock()));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
