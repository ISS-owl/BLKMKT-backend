package io.github.blkmkt.forum.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

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
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "主键")
	private Integer id;
	/**
	 * 用户昵称
	 */
	@ApiModelProperty(name = "nickname", value = "用户昵称")
	private String nickname;
	/**
	 * 手机号
	 */
	@ApiModelProperty(name = "tel", value = "手机号")
	private String tel;
	/**
	 * 密码
	 */
	@ApiModelProperty(name = "password", value = "密码")
	private String password;

}
