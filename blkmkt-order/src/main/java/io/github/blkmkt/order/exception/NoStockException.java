package io.github.blkmkt.order.exception;

public class NoStockException extends RuntimeException {
    public NoStockException(String msg) {
        super(msg);
    }

    public NoStockException(Integer id) {
        super("商品id: " + id + ", 库存不足");
    }
}
