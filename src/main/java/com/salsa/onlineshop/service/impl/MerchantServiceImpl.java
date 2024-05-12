package com.salsa.onlineshop.service.impl;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.PageResponseWrapper;
import com.salsa.onlineshop.dto.request.merchant.MerchantCreateRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantSearchRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantUpdateRequest;
import com.salsa.onlineshop.dto.response.merchant.MerchantResponse;
import com.salsa.onlineshop.entity.Merchant;
import com.salsa.onlineshop.repository.MerchantRepository;
import com.salsa.onlineshop.service.MerchantService;
import com.salsa.onlineshop.utils.specification.MerchantSpecification;
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
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Override
    public ControllerResponse<?> createMerchant(MerchantCreateRequest request) {
        Merchant merchant = Merchant.builder()
                .merchantName(request.getName())
                .build();
        merchantRepository.save(merchant);

        MerchantResponse merchantResponse = MerchantResponse.builder()
                .id(merchant.getId())
                .name(merchant.getMerchantName())
                .build();

        ControllerResponse<?> response = ControllerResponse.<MerchantResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Merchant Created")
                .data(merchantResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllMerchantWithPage(Pageable pageable, MerchantSearchRequest request) {
        Specification<Merchant> specification = MerchantSpecification.getSpecification(request);
        Page<Merchant> page = merchantRepository.findAll(specification, pageable);

        List<MerchantResponse> merchantResponseList = page.stream()
                .map(merchant -> MerchantResponse.builder()
                        .id(merchant.getId())
                        .name(merchant.getMerchantName())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(merchantResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Merchant List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> updateMerchant(MerchantUpdateRequest request) {
        Merchant existingMerchant = merchantRepository.findById(request.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.OK));

        existingMerchant = Merchant.builder()
                .id(existingMerchant.getId())
                .merchantName(request.getName())
                .build();
        merchantRepository.save(existingMerchant);

        MerchantResponse merchantResponse = MerchantResponse.builder()
                .id(existingMerchant.getId())
                .name(existingMerchant.getMerchantName())
                .build();

        ControllerResponse<MerchantResponse> response = ControllerResponse.<MerchantResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Merchant Updated")
                .data(merchantResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> deleteMerchant(String id) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.OK));
        merchantRepository.delete(merchant);

        ControllerResponse<?> response = ControllerResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .data("OK")
                .build();

        return response;
    }

    @Override
    public Merchant getMerchantById(String id) {
        if (merchantRepository.findById(id).isPresent()){
            return merchantRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }
}
