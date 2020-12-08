package io.github.blkmkt.user.exception;

public class PhoneNumExistException extends RuntimeException {
    public PhoneNumExistException() {
        super("该手机号已经被注册过！");
    }
}
