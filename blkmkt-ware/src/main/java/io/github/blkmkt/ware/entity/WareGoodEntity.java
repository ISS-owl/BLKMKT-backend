package io.github.blkmkt.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品库存表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
@Data
@TableName("ware_good")
public class WareGoodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id【主键】
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id【主键】")
	private Integer id;
	/**
	 * 货物id
	 */
	@ApiModelProperty(name = "goodId", value = "货物id")
	private Integer goodId;
	/**
	 * 剩余数量
	 */
	@ApiModelProperty(name = "stockNum", value = "剩余数量")
	private Integer stockNum;
	/**
	 * 锁定数量
	 */
	@ApiModelProperty(name = "stockLocked", value = "锁定数量")
	private Integer stockLocked;

}
