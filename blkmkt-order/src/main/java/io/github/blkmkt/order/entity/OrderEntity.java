package io.github.blkmkt.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 23:41:32
 */
@Data
@TableName("`order`")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	@TableId
	private Integer id;
	/**
	 * 货物id
	 */
	private Integer goodId;
	/**
	 * 用户id
	 */
	private Integer userId;

}
