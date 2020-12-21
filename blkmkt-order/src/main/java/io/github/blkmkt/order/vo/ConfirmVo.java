package io.github.blkmkt.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConfirmVo implements Serializable {
    UserVo consumer;

    GoodVo good;

    String orderToken;
}
