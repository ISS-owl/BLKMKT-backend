package io.github.issowl.search.vo;

import lombok.Data;

@Data
public class SearchParam {
    // 匹配字段
    private String keyword;

    // 过滤字段
    // _500, 1_500, 1_
    private String priceRange;

    private Integer gtype;

    private String category;

    private Integer hasStock;

    // 排序条件（字符串信息）
    // sort=[price/likeNum/createTime/updateTime]_[asc/desc]
    private String sort;

    // 页码
    private Integer pageNum = 1;
}
