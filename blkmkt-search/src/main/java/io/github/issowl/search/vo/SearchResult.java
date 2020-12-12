package io.github.issowl.search.vo;

import io.github.issowl.search.vo.model.GoodModel;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private List<GoodModel> goodModelList;

    private Long total;

    private Integer pageNum;

    private Integer totalPages;
}
