package io.github.common.exception;

public enum BizCodeEnum {
    // Login
    LOGIN_PASSWORD_EXCEPTION(11000, "账号或者密码错误"),
    USER_EXIST_EXCEPTION(11001, "该用户已经存在"),
    PHONE_EXIST_EXCEPTION(11002, "该手机号码已经被注册"),
    // Token
    TOKEN_INVALID(12000, "无效的Token"),
    TOKEN_SIGNATURE_INVALID(12001, "无效的签名"),
    TOKEN_EXPIRED(12002, "token已过期"),
    TOKEN_MISSION(12003, "token缺失"),
    REFRESH_TOKEN_INVALID(12004, "刷新Token无效"),
    TOKEN_ERROR(12005, "Token错误"),
    // Unknown
    UNKNOWN_ERROR(100000, "未知错误");

    private Integer code;

    private String msg;

    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
