package com.salsa.onlineshop.service;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.user.UserCreateRequest;
import com.salsa.onlineshop.dto.request.user.UserRewardUpdateRequest;
import com.salsa.onlineshop.dto.request.user.UserSearchRequest;
import com.salsa.onlineshop.dto.request.user.UserUpdateRequest;
import com.salsa.onlineshop.entity.Merchant;
import com.salsa.onlineshop.entity.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    ControllerResponse<?> register(UserCreateRequest request); //create
//    ControllerResponse<?> login(UserRequest request); //auth
    ControllerResponse<?> getAllUsersWithPage(Pageable pageable, UserSearchRequest request); //read
    ControllerResponse<?> updateUser(UserUpdateRequest request);
    ControllerResponse<?> deleteUser(String id);
    User getUserById(String id);
    ControllerResponse<?> updateRewardUser(UserRewardUpdateRequest request);
}
