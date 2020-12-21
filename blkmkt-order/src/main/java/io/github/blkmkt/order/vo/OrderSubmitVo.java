package io.github.blkmkt.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderSubmitVo implements Serializable {
    /**
     * 货物id
     */
    private Integer goodId;
    /**
     * 消费者id
     */
    private Integer consumerId;
    /**
     * 支付方式[1->支付宝; 2->微信; 3->货到付款]
     */
    private Integer payType;
    /**
     * 购买数量
     */
    private Integer goodNum;
    /**
     * 应付总金额
     */
    private Integer totalPrice;
    /**
     * 防重令牌
     */
    private String token;
}
