package io.github.blkmkt.good.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 货物评论
 * 
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 16:12:43
 */
@Data
@TableName("good_comment")
public class GoodCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id", example = "1")
	private Integer id;
	/**
	 * 
	 */
	@ApiModelProperty(name = "goodId", value = "货物id", example = "2")
	private Integer goodId;
	/**
	 * 
	 */
	@ApiModelProperty(name = "userId", value = "用户id", example = "3")
	private Integer userId;
	/**
	 * 
	 */
	@ApiModelProperty(name = "content", value = "内容")
	private String content;
	/**
	 * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
	 */
	@ApiModelProperty(name = "contentType", value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]", example = "1")
	private Integer contentType;
	/**
	 * 
	 */
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

}
