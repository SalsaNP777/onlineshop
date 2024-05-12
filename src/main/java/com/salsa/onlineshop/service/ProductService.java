package com.salsa.onlineshop.service;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.product.ProductCreateRequest;
import com.salsa.onlineshop.dto.request.product.ProductSearchRequest;
import com.salsa.onlineshop.dto.request.product.ProductUpdateRequest;
import com.salsa.onlineshop.entity.Merchant;
import com.salsa.onlineshop.entity.Product;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ControllerResponse<?> createProduct(ProductCreateRequest request);
    ControllerResponse<?> getAllProductsWithPage(Pageable pageable, ProductSearchRequest request);
    ControllerResponse<?> updateProduct(ProductUpdateRequest request);
    ControllerResponse<?> deleteProduct(String id);
    void updateProductStock(ProductUpdateRequest request);
    Product getProductById(String id);
}
