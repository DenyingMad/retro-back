package com.example.retro.app.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableQuery<T> {
    private List<T> content;
    private Integer totalPages;
    private Long totalItems;

    public PageableQuery(List<T> content) {
        this.content = content;
    }
}
