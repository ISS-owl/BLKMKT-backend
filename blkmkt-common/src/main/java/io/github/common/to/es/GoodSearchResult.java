package io.github.common.to.es;

import io.github.common.to.es.model.GoodModel;
import lombok.Data;

import java.util.List;

@Data
public class GoodSearchResult {
    private List<GoodModel> goodModelList;

    private Long total;

    private Integer pageNum;

    private Integer totalPages;
}
