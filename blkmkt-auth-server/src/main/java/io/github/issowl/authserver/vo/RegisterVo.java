package io.github.issowl.authserver.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVo {
    @ApiModelProperty(name = "studentId", value = "学生学号", example = "2018302080181")
    private String studentId;

    @ApiModelProperty(name = "name", value = "学生姓名", example = "王涛")
    private String name;

    @ApiModelProperty(name = "password", value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(name = "phone", value = "手机号", example = "18268795310")
    private String phone;
}
