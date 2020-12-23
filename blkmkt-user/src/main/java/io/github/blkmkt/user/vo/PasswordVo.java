package io.github.blkmkt.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordVo {
    @ApiModelProperty(name = "id", value = "用户id", example = "1", required = true)
    private String id;

    @ApiModelProperty(name = "old password", value = "旧密码", example = "qwerty", required = true)
    private String oldPassword;

    @ApiModelProperty(name = "new password", value = "新密码", example = "123456", required = true)
    private String newPassword;
}
