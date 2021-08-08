package com.example.retro.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingDto {
    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private long totalItemsCount;
}
