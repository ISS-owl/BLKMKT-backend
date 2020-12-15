package io.github.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PageParam implements Serializable {
    Long page;

    Long limit;

    String sidx;

    String order;

    public PageParam(Long page, Long limit) {
        this.page = page;
        this.limit = limit;
        this.sidx = null;
        this.order = null;
    }
}
