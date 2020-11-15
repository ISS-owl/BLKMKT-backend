package io.github.blkmkt.good.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 货物表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
@Data
@TableName("good")
public class GoodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 货物id
	 */
	@TableId
	private Integer id;
	/**
	 * 货物名称
	 */
	private String name;
	/**
	 * 卖家id
	 */
	private Integer ownerId;
	/**
	 * 价格
	 */
	private Integer price;
	/**
	 * 分类
	 */
	private String gtype;
	/**
	 * 总数
	 */
	private Integer totalNum;
	/**
	 * 当前数量
	 */
	private Integer currentNum;
	/**
	 * 商品图片的url
	 */
	private String goodImgUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 取消时间
	 */
	private Date cancelTime;

}
