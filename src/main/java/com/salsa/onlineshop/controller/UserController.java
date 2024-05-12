package com.salsa.onlineshop.controller;

import com.salsa.onlineshop.dto.ControllerResponse;
import com.salsa.onlineshop.dto.request.user.UserCreateRequest;
import com.salsa.onlineshop.dto.request.user.UserSearchRequest;
import com.salsa.onlineshop.dto.request.user.UserUpdateRequest;
import com.salsa.onlineshop.service.UserService;
import com.salsa.onlineshop.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserCreateRequest request){
        ControllerResponse<?> response =userService.register(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

        return result;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserWithPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ModelAttribute UserSearchRequest request
    ){
        Pageable pageable = PageRequest.of(page, size);
        ControllerResponse<?> response = userService.getAllUsersWithPage(pageable, request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        ControllerResponse<?> response = userService.updateUser(request);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        ControllerResponse<?> response = userService.deleteUser(id);

        ResponseEntity result = ResponseEntity.status(HttpStatus.OK)
                .body(response);

        return result;
    }
}
