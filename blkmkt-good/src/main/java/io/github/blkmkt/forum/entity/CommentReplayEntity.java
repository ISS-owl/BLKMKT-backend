package io.github.blkmkt.forum.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-13 19:36:24
 */
@Data
@TableName("comment_replay")
public class CommentReplayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id")
	private Integer id;
	/**
	 * 评论id
	 */
	@ApiModelProperty(name = "commentId", value = "评论id")
	private Integer commentId;
	/**
	 * 用户id
	 */
	@ApiModelProperty(name = "userId", value = "用户id")
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
