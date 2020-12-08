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
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
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
	@ApiModelProperty(name = "reply", value = "评论内容")
	private String reply;

}
