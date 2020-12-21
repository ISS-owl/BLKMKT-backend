package io.github.common.to;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTo {
    /**
     * 订单号
     */
    private Integer id;
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
     * 订单状态[0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单]
     */
    private Integer status;
    /**
     * 购买数量
     */
    private Integer goodNum;
    /**
     * 应付总金额
     */
    private Integer totalPrice;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
