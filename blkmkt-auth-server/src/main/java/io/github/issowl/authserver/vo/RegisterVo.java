package io.github.issowl.authserver.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterVo {
    private String studentId;

    private String name;

    private String nickname;

    private String password;

    private String address;

    private Integer sex;

    private String phone;
}
