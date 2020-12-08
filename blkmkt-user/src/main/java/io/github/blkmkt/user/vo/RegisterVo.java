package io.github.blkmkt.user.vo;

import lombok.Data;

@Data
public class RegisterVo {
    private String studentId;

    private String name;

    private String password;

    private String phone;
}
