package com.salsa.onlineshop.service;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.transaction.TransactionCreateRequest;
import com.salsa.onlineshop.dto.request.transaction.TransactionSearchRequest;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    ControllerResponse<?> createTransaction(TransactionCreateRequest request);
    ControllerResponse<?> getAllTransactionWithPage(Pageable pageable, TransactionSearchRequest request);
}
