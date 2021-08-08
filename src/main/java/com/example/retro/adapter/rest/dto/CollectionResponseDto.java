package com.example.retro.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResponseDto<T> {
    private List<T> data;
    private PagingDto paging;
}
