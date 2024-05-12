package com.salsa.onlineshop.service.impl;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.PageResponseWrapper;
import com.salsa.onlineshop.dto.request.product.ProductUpdateRequest;
import com.salsa.onlineshop.dto.request.transaction.TransactionCreateRequest;
import com.salsa.onlineshop.dto.request.transaction.TransactionSearchRequest;
import com.salsa.onlineshop.dto.request.user.UserRewardUpdateRequest;
import com.salsa.onlineshop.dto.response.transaction.TransactionResponse;
import com.salsa.onlineshop.entity.*;
import com.salsa.onlineshop.repository.TransactionRepository;
import com.salsa.onlineshop.service.*;
import com.salsa.onlineshop.utils.specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final MerchantService merchantService;
    private final RewardService rewardService;
    private final ProductService productService;

    @Override
    public ControllerResponse<?> createTransaction(TransactionCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Merchant merchant = merchantService.getMerchantById(request.getMerchantId());
        Reward reward = rewardService.getRewardById(request.getRewardId());
        Product product = productService.getProductById(request.getProductId());

        if (product.getProductStock() < request.getAmount()) {
            return ControllerResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("Product is out of stock")
                    .build();
        }

        int totalPrice = product.getProductPrice() * request.getAmount();

        int updatedTotalReward = reward.getRewardPoint() + (user.getTotalReward() != null ? user.getTotalReward() : 0);
        UserRewardUpdateRequest userRewardUpdateRequest = new UserRewardUpdateRequest();
        userRewardUpdateRequest.setId(user.getId());
        userRewardUpdateRequest.setTotalReward(updatedTotalReward);
        userService.updateRewardUser(userRewardUpdateRequest);

        ProductUpdateRequest updateRequest = ProductUpdateRequest.builder()
                .id(product.getId())
                .stock(product.getProductStock() - request.getAmount())
                .build();
        productService.updateProductStock(updateRequest);

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .totalPrice(totalPrice)
                .userId(user)
                .productId(product)
                .merchantId(merchant)
                .rewardId(reward)
                .build();
        transactionRepository.save(transaction);

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .amount(transaction.getAmount())
                .totalPrice(transaction.getTotalPrice())
                .userId(user.getId())
                .productId(product.getId())
                .merchantId(merchant.getId())
                .rewardId(reward.getId())
                .build();

        ControllerResponse<?> response = ControllerResponse.<TransactionResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Transaction Created")
                .data(transactionResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllTransactionWithPage(Pageable pageable, TransactionSearchRequest request) {
        Specification<Transaction> specification = TransactionSpecification.getSpecification(request);
        Page<Transaction> page = transactionRepository.findAll(specification, pageable);

        List<TransactionResponse> transactionResponseList = page.stream()
                .map(transaction -> TransactionResponse.builder()
                        .id(transaction.getId())
                        .amount(transaction.getAmount())
                        .totalPrice(transaction.getTotalPrice())
                        .userId(transaction.getUserId().getId())
                        .productId(transaction.getProductId().getId())
                        .merchantId(transaction.getMerchantId().getId())
                        .rewardId(transaction.getRewardId().getId())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(transactionResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Transaction List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }
}
