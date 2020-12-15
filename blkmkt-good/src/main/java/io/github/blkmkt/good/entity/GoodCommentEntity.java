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
	 * id
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id", example = "1")
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
	/**
	 * 内容
	 */
	@ApiModelProperty(name = "content", value = "内容")
	private String content;
    /**
     * 点赞数
     */
    @ApiModelProperty(name = "likeNum", value = "点赞数", example = "0")
    private Integer likeNum;
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
