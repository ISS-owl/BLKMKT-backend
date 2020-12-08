package io.github.blkmkt.user.exception;

public class UserExistException extends RuntimeException {
    public UserExistException() {
        super("该用户已经存在！");
    }
}
