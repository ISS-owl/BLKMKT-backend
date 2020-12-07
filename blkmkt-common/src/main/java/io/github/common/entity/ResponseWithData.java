package io.github.common.entity;

public class ResponseWithData<T> extends Response {
    private T data;

    public ResponseWithData(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
