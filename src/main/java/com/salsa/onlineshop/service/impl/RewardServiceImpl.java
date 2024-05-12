package com.salsa.onlineshop.service.impl;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.PageResponseWrapper;
import com.salsa.onlineshop.dto.request.reward.RewardCreateRequest;
import com.salsa.onlineshop.dto.request.reward.RewardSearchRequest;
import com.salsa.onlineshop.dto.request.reward.RewardUpdateRequest;
import com.salsa.onlineshop.dto.response.product.ProductResponse;
import com.salsa.onlineshop.dto.response.reward.RewardResponse;
import com.salsa.onlineshop.entity.Product;
import com.salsa.onlineshop.entity.Reward;
import com.salsa.onlineshop.repository.RewardRepository;
import com.salsa.onlineshop.service.RewardService;
import com.salsa.onlineshop.utils.specification.RewardSpecification;
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
public class RewardServiceImpl implements RewardService {
    private final RewardRepository rewardRepository;

    @Override
    public ControllerResponse<?> createReward(RewardCreateRequest request) {
        Reward reward = Reward.builder()
                .rewardName(request.getName())
                .rewardPoint(request.getPoint())
                .build();
        rewardRepository.save(reward);

        RewardResponse rewardResponse = RewardResponse.builder()
                .id(reward.getId())
                .name(reward.getRewardName())
                .point(reward.getRewardPoint())
                .build();

        ControllerResponse<?> response = ControllerResponse.<RewardResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Reward Created")
                .data(rewardResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllRewardsWithPage(Pageable pageable, RewardSearchRequest request) {
        Specification<Reward> specification = RewardSpecification.getSpecification(request);
        Page<Reward> page = rewardRepository.findAll(specification, pageable);

        List<RewardResponse> rewardResponseList = page.stream()
                .map(reward -> RewardResponse.builder()
                        .id(reward.getId())
                        .name(reward.getRewardName())
                        .point(reward.getRewardPoint())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(rewardResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Rewards List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> updateReward(RewardUpdateRequest request) {
        Reward existingReward = rewardRepository.findById(request.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.OK));

        existingReward = Reward.builder()
                .id(existingReward.getId())
                .rewardName(request.getName())
                .rewardPoint(request.getPoint())
                .build();
        rewardRepository.save(existingReward);

        RewardResponse rewardResponse = RewardResponse.builder()
                .id(existingReward.getId())
                .name(existingReward.getRewardName())
                .point(existingReward.getRewardPoint())
                .build();

        ControllerResponse<RewardResponse> response = ControllerResponse.<RewardResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Reward Updated")
                .data(rewardResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> deleteReward(String id) {
        Reward reward = rewardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
        rewardRepository.delete(reward);

        ControllerResponse<?> response = ControllerResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .data("OK")
                .build();

        return response;
    }

    @Override
    public Reward getRewardById(String id) {
        if (rewardRepository.findById(id).isPresent()){
            return rewardRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }

    @Override
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }
}
