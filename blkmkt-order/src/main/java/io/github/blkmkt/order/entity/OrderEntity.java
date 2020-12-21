package io.github.blkmkt.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 20:22:03
 */
@Data
@TableName("`order`")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "订单号", example = "1")
	private Integer id;
	/**
	 * 货物id
	 */
	@ApiModelProperty(name = "goodId", value = "货物id", example = "2")
	private Integer goodId;
	/**
     * 消费者id
     */
    @ApiModelProperty(name = "consumerId", value = "消费者id", example = "3")
    private Integer consumerId;
    /**
     * 支付方式[1->支付宝; 2->微信; 3->货到付款]
     */
    @ApiModelProperty(name = "payType", value = "支付方式", example = "3")
    private Integer payType;
    /**
     * 订单状态[0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单]
     */
    @ApiModelProperty(name = "status", value = "订单状态", example = "1")
    private Integer status;
    /**
     * 购买数量
     */
    @ApiModelProperty(name = "goodNum", value = "购买数量", example = "1")
    private Integer goodNum;
    /**
     * 应付总金额
     */
    @ApiModelProperty(name = "totalPrice", value = "应付总金额", example = "100")
    private Integer totalPrice;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;
}
