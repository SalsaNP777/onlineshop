package com.salsa.onlineshop.controller;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.product.ProductCreateRequest;
import com.salsa.onlineshop.dto.request.product.ProductSearchRequest;
import com.salsa.onlineshop.dto.request.product.ProductUpdateRequest;
import com.salsa.onlineshop.service.ProductService;
import com.salsa.onlineshop.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest request){
        ControllerResponse<?> response = productService.createProduct(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllProductsWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute ProductSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = productService.getAllProductsWithPage(pageable, request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request){
        ControllerResponse<?> response = productService.updateProduct(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        ControllerResponse<?> response = productService.deleteProduct(id);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }
}
