package com.salsa.onlineshop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ControllerResponse <T>{
    private String status;
    private String message;
    private T data;
}
