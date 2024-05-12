package com.salsa.onlineshop.service;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.merchant.MerchantCreateRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantSearchRequest;
import com.salsa.onlineshop.dto.request.merchant.MerchantUpdateRequest;
import com.salsa.onlineshop.entity.Merchant;
import org.springframework.data.domain.Pageable;

import javax.naming.ldap.Control;

public interface MerchantService {
    ControllerResponse<?> createMerchant(MerchantCreateRequest request);
    ControllerResponse<?> getAllMerchantWithPage(Pageable pageable, MerchantSearchRequest request);
    ControllerResponse<?> updateMerchant(MerchantUpdateRequest request);
    ControllerResponse<?> deleteMerchant(String id);
    Merchant getMerchantById(String id);
}
