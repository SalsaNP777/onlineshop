package com.salsa.onlineshop.controller;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.reward.RewardCreateRequest;
import com.salsa.onlineshop.dto.request.reward.RewardSearchRequest;
import com.salsa.onlineshop.dto.request.reward.RewardUpdateRequest;
import com.salsa.onlineshop.service.RewardService;
import com.salsa.onlineshop.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.REWARD)
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    @PostMapping
    public ResponseEntity<?> createReward(@RequestBody RewardCreateRequest request){
        ControllerResponse<?> response = rewardService.createReward(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllRewardsWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute RewardSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = rewardService.getAllRewardsWithPage(pageable, request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReward(@PathVariable String id, @RequestBody RewardUpdateRequest request){
        ControllerResponse<?> response = rewardService.updateReward(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReward(@PathVariable String id){
        ControllerResponse<?> response = rewardService.deleteReward(id);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }
}
