package com.salsa.onlineshop.controller;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.merchant.MerchantCreateRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantSearchRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantUpdateRequest;
import com.salsa.onlineshop.service.MerchantService;
import com.salsa.onlineshop.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.MERCHANT)
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody MerchantCreateRequest request){
        ControllerResponse<?> response = merchantService.createMerchant(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllMerchantWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute MerchantSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = merchantService.getAllMerchantWithPage(pageable, request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable String id, @RequestBody MerchantUpdateRequest request){
        ControllerResponse<?> response = merchantService.updateMerchant(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable String id){
        ControllerResponse<?> response = merchantService.deleteMerchant(id);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }
}
