package com.salsa.onlineshop.service;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.reward.RewardCreateRequest;
import com.salsa.onlineshop.dto.request.reward.RewardSearchRequest;
import com.salsa.onlineshop.dto.request.reward.RewardUpdateRequest;
import com.salsa.onlineshop.entity.Merchant;
import com.salsa.onlineshop.entity.Reward;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RewardService {
    ControllerResponse<?> createReward(RewardCreateRequest request);
    ControllerResponse<?> getAllRewardsWithPage(Pageable pageable, RewardSearchRequest request);
    ControllerResponse<?> updateReward(RewardUpdateRequest request);
    ControllerResponse<?> deleteReward(String id);
    Reward getRewardById(String id);
    List<Reward> getAllRewards();
}
