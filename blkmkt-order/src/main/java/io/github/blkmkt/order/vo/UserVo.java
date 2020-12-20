package io.github.blkmkt.order.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId
    @ApiModelProperty(name = "id", value = "用户id", example = "1")
    private Integer id;
    /**
     * 用户姓名
     */
    @ApiModelProperty(name = "name", value = "用户姓名", example = "张三")
    private String name;
    /**
     * 用户密码
     */
    @ApiModelProperty(name = "password", value = "用户密码", example = "114514")
    private String password;
    /**
     * 用户学号
     */
    @ApiModelProperty(name = "studentId", value = "用户学号", example = "2018302080181")
    private String studentId;
    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickname", value = "用户昵称", example = "李四")
    private String nickname;
    /**
     * 用户头像
     */
    @ApiModelProperty(name = "headImgUrl", value = "用户头像", example = "baidu.com")
    private String headImgUrl;
    /**
     * 用户手机号
     */
    @ApiModelProperty(name = "mobile", value = "用户手机号", example = "18379873072")
    private String mobile;
    /**
     * 用户性别
     */
    @ApiModelProperty(name = "sex", value = "用户性别", example = "1")
    private Integer sex;
    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用", example = "1")
    private Integer enabled;
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
    /**
     * 用户地址
     */
    @ApiModelProperty(name = "address", value = "用户地址", example = "信13-613")
    private String address;
    /**
     * 用户描述
     */
    @ApiModelProperty(name = "description", value = "用户描述", example = "HelloWorld")
    private String description;
}
