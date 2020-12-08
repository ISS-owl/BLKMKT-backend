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
@TableName("permission")
public class PermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "id", value = "")
	private Integer id;
	/**
	 * 权限名
	 */
	@ApiModelProperty(name = "name", value = "权限名")
	private String name;
	/**
	 * 权限对应的uri
	 */
	@ApiModelProperty(name = "uri", value = "权限对应的uri")
	private String uri;

}
