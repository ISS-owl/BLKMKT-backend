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
<<<<<<< HEAD
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
=======
 * @author YuNing Wu
 * @email 414085716@qq.com
>>>>>>> 6ae64d4 (feat:complete forum module)
 * @date 2020-12-19 12:53:38
 */
@Data
@TableName("article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "主键")
	private Integer id;
	/**
	 * 外键->表user
	 */
	@ApiModelProperty(name = "userid", value = "外键->表user")
	private Integer userid;
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
	 * 点踩数
	 */
	@ApiModelProperty(name = "dislikeNum", value = "点踩数")
	private Integer dislikeNum;
	/**
	 * 文章标题
文章标题
	 */
	@ApiModelProperty(name = "title", value = "文章标题")
	private String title;
	/**
	 * 文章作者
	 */
	@ApiModelProperty(name = "author", value = "文章作者")
	private String author;
	/**
	 * 发布时间
	 */
	@ApiModelProperty(name = "date", value = "发布时间")
	private Date date;
	/**
	 * 文章内容
	 */
	@ApiModelProperty(name = "content", value = "文章内容")
	private String content;

}
