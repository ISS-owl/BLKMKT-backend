package io.github.blkmkt.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WareLockOrderVo implements Serializable {
    private Integer orderId;

    private GoodVo lockedGood;

    private Integer goodNum;
}
