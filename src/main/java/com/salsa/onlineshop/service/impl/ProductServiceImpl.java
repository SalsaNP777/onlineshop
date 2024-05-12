package com.salsa.onlineshop.service.impl;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.PageResponseWrapper;
import com.salsa.onlineshop.dto.request.product.ProductCreateRequest;
import com.salsa.onlineshop.dto.request.product.ProductSearchRequest;
import com.salsa.onlineshop.dto.request.product.ProductUpdateRequest;
import com.salsa.onlineshop.dto.response.product.ProductResponse;
import com.salsa.onlineshop.entity.Merchant;
import com.salsa.onlineshop.entity.Product;
import com.salsa.onlineshop.repository.ProductRepository;
import com.salsa.onlineshop.service.MerchantService;
import com.salsa.onlineshop.service.ProductService;
import com.salsa.onlineshop.utils.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private  final MerchantService merchantService;

    @Override
    public ControllerResponse<?> createProduct(ProductCreateRequest request) {
        Merchant merchant = merchantService.getMerchantById(request.getMerchantId());

        Product product = Product.builder()
                .productName(request.getName())
                .productPrice(request.getPrice())
                .productStock(request.getStock())
                .merchantId(merchant)
                .build();
        productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getProductName())
                .price(product.getProductPrice())
                .stock(product.getProductStock())
                .merchantId(merchant.getId())
                .build();

        ControllerResponse<?> response = ControllerResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Product Created")
                .data(productResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllProductsWithPage(Pageable pageable, ProductSearchRequest request) {
        Specification<Product> specification = ProductSpecification.getSpecification(request);
        Page<Product> page = productRepository.findAll(specification, pageable);

        List<ProductResponse> productResponseList = page.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getProductName())
                        .price(product.getProductPrice())
                        .stock(product.getProductStock())
                        .merchantId(product.getMerchantId().getId())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(productResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Products List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> updateProduct(ProductUpdateRequest request) {
        Product existingProduct = productRepository.findById(request.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.OK));

        existingProduct = Product.builder()
                .id(existingProduct.getId())
                .productName(request.getName())
                .productPrice(request.getPrice())
                .productStock(request.getStock())
                .build();
        productRepository.save(existingProduct);

        ProductResponse productResponse = ProductResponse.builder()
                .id(existingProduct.getId())
                .name(existingProduct.getProductName())
                .price(existingProduct.getProductPrice())
                .stock(existingProduct.getProductStock())
                .merchantId(existingProduct.getMerchantId().getId())
                .build();

        ControllerResponse<ProductResponse> response = ControllerResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Product Updated")
                .data(productResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.OK));
        productRepository.delete(product);

        ControllerResponse<?> response = ControllerResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .data("OK")
                .build();

        return response;
    }

    @Override
    public void updateProductStock(ProductUpdateRequest updateRequest) {
        Product product = getProductById(updateRequest.getId());
        product.setProductStock(updateRequest.getStock());
        productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        if (productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }
}
