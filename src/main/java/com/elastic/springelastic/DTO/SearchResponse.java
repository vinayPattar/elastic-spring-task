package com.elastic.springelastic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse<T> {
    private long total;
    private List<T> content;
}
