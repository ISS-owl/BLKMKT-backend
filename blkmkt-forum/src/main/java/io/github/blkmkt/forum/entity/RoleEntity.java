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
@TableName("role")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "主键")
	private Integer id;
	/**
	 * 角色名称
	 */
	@ApiModelProperty(name = "role", value = "角色名称")
	private String role;
	/**
	 * 角色等级超级管理员（3），管理员（2），普通会员（1）
	 */
	@ApiModelProperty(name = "level", value = "角色等级超级管理员（3），管理员（2），普通会员（1）")
	private Integer level;

}
