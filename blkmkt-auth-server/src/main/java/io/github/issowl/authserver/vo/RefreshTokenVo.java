package io.github.issowl.authserver.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RefreshTokenVo {
    @ApiModelProperty(name = "studentId", value = "学生学号", example = "2018302080181")
    private String studentId;

    @ApiModelProperty(name = "refreshToken", value = "刷新令牌", example = "123456")
    private String refreshToken;
}
