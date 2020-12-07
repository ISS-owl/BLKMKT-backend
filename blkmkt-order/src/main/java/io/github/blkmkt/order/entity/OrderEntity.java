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
@TableName("order")
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
	 * 用户id
	 */
	@ApiModelProperty(name = "userId", value = "用户id", example = "3")
	private Integer userId;

}
