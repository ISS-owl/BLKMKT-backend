package io.github.blkmkt.good.entity;

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
 * @date 2020-12-07 16:12:43
 */
@Data
@TableName("good_comment_replay")
public class GoodCommentReplayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "id", example = "1")
	private Long id;
	/**
	 * 评论id
	 */
	@ApiModelProperty(name = "commentId", value = "评论id", example = "2")
	private Long commentId;
	/**
	 * 回复id
	 */
	@ApiModelProperty(name = "replyId", value = "回复id", example = "3")
	private Long replyId;

}
