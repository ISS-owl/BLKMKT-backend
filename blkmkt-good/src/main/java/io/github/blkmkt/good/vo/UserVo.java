package io.github.blkmkt.good.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Integer id;

    private String name;

    private String password;

    private String studentId;

    private String nickname;

    private String headImgUrl;

    private String mobile;

    private Integer sex;

    private Integer enabled;

    private Date createTime;

    private Date updateTime;

    private String address;

    private String description;
}
