package com.salsa.onlineshop.service.impl;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.PageResponseWrapper;
import com.salsa.onlineshop.dto.request.user.UserCreateRequest;
import com.salsa.onlineshop.dto.request.user.UserRewardUpdateRequest;
import com.salsa.onlineshop.dto.request.user.UserSearchRequest;
import com.salsa.onlineshop.dto.request.user.UserUpdateRequest;
import com.salsa.onlineshop.dto.response.user.UserResponse;
import com.salsa.onlineshop.entity.User;
import com.salsa.onlineshop.repository.UserRepository;
import com.salsa.onlineshop.service.UserService;
import com.salsa.onlineshop.utils.specification.UserSpecification;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ControllerResponse<?> register(UserCreateRequest request) {
        User user = User.builder()
                .userName(request.getName())
                .userEmail(request.getEmail())
                .userPassword(request.getPassword())
                .build();
        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getUserName())
                .email(user.getUserEmail())
                .password(user.getUserPassword())
                .totalReward(user.getTotalReward())
                .build();

        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("User Created")
                .data(userResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> getAllUsersWithPage(Pageable pageable, UserSearchRequest request) {
        Specification<User> specification = UserSpecification.getSpecification(request);
        Page<User> page = userRepository.findAll(specification, pageable);

        List<UserResponse> userResponseList = page.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getUserEmail())
                        .name(user.getUserName())
                        .password(user.getUserPassword())
                        .totalReward(user.getTotalReward())
                        .build())
                .collect(Collectors.toList());

        PageResponseWrapper pageResponseWrapper = PageResponseWrapper.builder()
                .data(userResponseList)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .size(page.getSize())
                .build();

        ControllerResponse<?> response = ControllerResponse.<PageResponseWrapper>builder()
                .message("Users List")
                .data(pageResponseWrapper)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> updateUser(UserUpdateRequest request) {
        User existingUser = userRepository.findById(request.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.OK));

        existingUser = User.builder()
                .id(existingUser.getId())
                .userName(request.getName())
                .userEmail(request.getEmail())
                .userPassword(request.getPassword())
                .build();
        userRepository.save(existingUser);

        UserResponse userResponse = UserResponse.builder()
                .id(existingUser.getId())
                .name(existingUser.getUserName())
                .email(existingUser.getUserEmail())
                .password(existingUser.getUserPassword())
                .totalReward(existingUser.getTotalReward())
                .build();

        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("User Updated")
                .data(userResponse)
                .build();

        return response;
    }

    @Override
    public ControllerResponse<?> deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
        userRepository.delete(user);

        ControllerResponse<?> response = ControllerResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .data("OK")
                .build();

        return response;
    }

    @Override
    public User getUserById(String id) {
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }else throw new RuntimeException("DATA NOT FOUND");
    }

    @Override
    public ControllerResponse<?> updateRewardUser(UserRewardUpdateRequest request) {
        User existingUser = userRepository.findById(request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
        existingUser.setTotalReward(request.getTotalReward());
        userRepository.save(existingUser);

        UserResponse userResponse = UserResponse.builder()
                .id(existingUser.getId())
                .name(existingUser.getUserName())
                .email(existingUser.getUserEmail())
                .password(existingUser.getUserPassword())
                .totalReward(existingUser.getTotalReward())
                .build();

        ControllerResponse<UserResponse> response = ControllerResponse.<UserResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("User reward updated successfully")
                .data(userResponse)
                .build();

        return response;
    }
}
