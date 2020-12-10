package io.github.blkmkt.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterVo {
    @NotNull(message = "学号不为空")
    @Length(min = 13, max = 13, message = "学号长度不符合规范") // Length 13
    @ApiModelProperty(name = "studentId", value = "学生学号", example = "2018302080181", required = true)
    private String studentId;

    @NotNull(message = "姓名不为空")
    @Length(min = 2, max = 5, message = "姓名长度必须为2~5个字符（请填写中文名）")
    @ApiModelProperty(name = "name", value = "学生姓名", example = "王涛", required = true)
    private String name;

    @NotNull
    @Length(min = 1, max = 20, message = "昵称长度必须为1~20个字符")
    @ApiModelProperty(name = "nickname", value = "昵称", example = "wt", required = true)
    private String nickname;

    @NotNull
    @ApiModelProperty(name = "password", value = "密码", example = "123456", required = true)
    private String password;

    @NotNull(message = "地址不能为空")
    @ApiModelProperty(name = "address", value = "地址", example = "信13-613", required = true)
    private String address;

    @Range(min = 0, max = 2, message = "性别不符合规范（0：保密，1：男，2：女）")
    @ApiModelProperty(name = "sex", value = "性别", example = "1")
    private Integer sex;

    @Pattern(regexp = "1[34578][0-9]\\d{8}", message = "手机号不符合规范")
    @ApiModelProperty(name = "phone", value = "手机号", example = "18268795310")
    private String phone;
}
