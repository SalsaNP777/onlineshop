package com.salsa.onlineshop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponseWrapper <T>{
    private T data;
    private Long totalElement;
    private Integer totalPage;
    private Integer currentPage;
    private Integer size;
}
