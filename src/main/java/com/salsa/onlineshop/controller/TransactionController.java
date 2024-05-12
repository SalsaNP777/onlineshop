package com.salsa.onlineshop.controller;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.transaction.TransactionCreateRequest;
import com.salsa.onlineshop.dto.request.transaction.TransactionSearchRequest;
import com.salsa.onlineshop.service.TransactionService;
import com.salsa.onlineshop.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionCreateRequest request){
        ControllerResponse<?> response = transactionService.createTransaction(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactionWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute TransactionSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = transactionService.getAllTransactionWithPage(pageable, request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }
}
