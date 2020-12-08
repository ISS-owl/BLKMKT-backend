package io.github.common.exception;

public enum BizCodeEnum {
    LOGIN_PASSWORD_EXCEPTION(11000, "账号或者密码错误"),
    USER_EXIST_EXCEPTION(11001, "该用户已经存在"),
    PHONE_EXIST_EXCEPTION(11002, "该手机号码已经被注册");

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
