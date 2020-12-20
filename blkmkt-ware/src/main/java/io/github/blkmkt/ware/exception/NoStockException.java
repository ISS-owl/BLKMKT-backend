package io.github.blkmkt.ware.exception;

public class NoStockException extends RuntimeException {
    public NoStockException(String msg) {
        super(msg);
    }

    public NoStockException(Integer id) {
        super("商品id: " + id + ", 库存不足");
    }
}
