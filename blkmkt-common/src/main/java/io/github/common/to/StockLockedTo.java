package io.github.common.to;

import lombok.Data;

@Data
public class StockLockedTo {
    /**
     * id【主键】
     */
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 货物id
     */
    private Integer goodId;
    /**
     * 购买数量
     */
    private Integer goodNum;
    /**
     * 状态【1-已锁定  2-已解锁  3-扣减】
     */
    private Integer lockStatus;

}
