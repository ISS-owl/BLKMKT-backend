package io.github.blkmkt.good.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 货物表
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 16:12:43
 */
@Data
@TableName("good")
public class GoodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 货物id
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "货物id", example = "1")
	private Integer id;
	/**
	 * 货物名称
	 */
	@ApiModelProperty(name = "name", value = "货物名称", example = "华为手机")
	private String name;
	/**
	 * 卖家id
	 */
	@ApiModelProperty(name = "ownerId", value = "卖家id", example = "2")
	private Integer ownerId;
	/**
	 * 价格
	 */
	@ApiModelProperty(name = "price", value = "价格", example = "4399")
	private Integer price;
	/**
	 * 分类
	 */
	@ApiModelProperty(name = "gtype", value = "分类", example = "国创")
	private String gtype;
	/**
	 * 总数
	 */
	@ApiModelProperty(name = "totalNum", value = "总数", example = "2333")
	private Integer totalNum;
	/**
	 * 当前数量
	 */
	@ApiModelProperty(name = "currentNum", value = "当前数量", example = "233")
	private Integer currentNum;
	/**
	 * 商品图片的url
	 */
	@ApiModelProperty(name = "goodImgUrl", value = "商品图片的url", example = "baidu.com")
	private String goodImgUrl;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;
	/**
	 * 取消时间
	 */
	@ApiModelProperty(name = "cancelTime", value = "取消时间")
	private Date cancelTime;

}
