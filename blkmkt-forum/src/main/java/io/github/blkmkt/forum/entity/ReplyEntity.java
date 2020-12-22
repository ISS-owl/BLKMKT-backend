package io.github.blkmkt.forum.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author YuNing Wu
 * @email 414085716@qq.com
 * @date 2020-12-19 12:53:38
 */
@Data
@TableName("reply")
public class ReplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "主键")
	private Integer id;
	/**
	 * 外键->表article
	 */
	@ApiModelProperty(name = "articleId", value = "外键->表article")
	private Integer articleId;
	/**
	 * 0为文章评论，非0为被回复评论id
	 */
	@ApiModelProperty(name = "preId", value = "0为文章评论，非0为被回复评论id")
	private Integer preId;
	/**
	 * 根评论id，若为一级评论则为0
	 */
	@ApiModelProperty(name = "firstId", value = "根评论id，若为一级评论则为0")
	private Integer firstId;
	/**
	 * 是否被举报，1为举报
	 */
	@ApiModelProperty(name = "able", value = "是否被举报，1为举报")
	private Integer able;
	/**
	 * 点赞数
	 */
	@ApiModelProperty(name = "likeNum", value = "点赞数")
	private Integer likeNum;
	/**
	 * 评论人
	 */
	@ApiModelProperty(name = "replyer", value = "评论人")
	private String replyer;
	/**
	 * 评论时间
	 */
	@ApiModelProperty(name = "date", value = "评论时间")
	private Date date;
	/**
	 * 评论内容
	 */
	@ApiModelProperty(name = "content", value = "评论内容")
	private String content;

}
