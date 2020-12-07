package io.github.common.entity;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Value
public class PageParam implements Serializable {
    Long page;

    Long limit;

    String sidx;

    String order;
}
