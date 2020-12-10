package io.github.blkmkt.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVo {
    @ApiModelProperty(name = "studentId", value = "学生学号", example = "2018302080181", required = true)
    private String studentId;

    @ApiModelProperty(name = "name", value = "学生姓名", example = "王涛", required = true)
    private String name;

    @ApiModelProperty(name = "nickname", value = "昵称", example = "wt", required = true)
    private String nickname;

    @ApiModelProperty(name = "password", value = "密码", example = "123456", required = true)
    private String password;

    @ApiModelProperty(name = "address", value = "地址", example = "信13-613", required = true)
    private String address;

    @ApiModelProperty(name = "sex", value = "性别", example = "1")
    private String sex;

    @ApiModelProperty(name = "phone", value = "手机号", example = "18268795310")
    private String phone;
}
