package io.github.blkmkt.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品订单信息
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
@Data
@TableName("ware_order")
public class WareOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id【主键】
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id【主键】")
	private Integer id;
	/**
	 * 订单id
	 */
	@ApiModelProperty(name = "orderId", value = "订单id")
	private Integer orderId;
	/**
	 * 货物id
	 */
	@ApiModelProperty(name = "goodId", value = "货物id")
	private Integer goodId;
	/**
	 * 购买数量
	 */
	@ApiModelProperty(name = "goodNum", value = "购买数量")
	private Integer goodNum;
	/**
	 * 状态【1-已锁定  2-已解锁  3-扣减】
	 */
	@ApiModelProperty(name = "lockStatus", value = "状态【1-已锁定  2-已解锁  3-扣减】")
	private Integer lockStatus;

}
