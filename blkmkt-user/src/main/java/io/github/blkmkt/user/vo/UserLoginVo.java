package io.github.blkmkt.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginVo {
    @ApiModelProperty(name = "studentId", value = "学生学号", example = "2018302080181")
    private String studentId;

    @ApiModelProperty(name = "password", value = "密码", example = "123456")
    private String password;
}
