package io.github.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    @ApiModelProperty(name = "code", example = "200")
    protected Integer code;

    @ApiModelProperty(name = "msg", example = "success")
    protected String msg;
}
