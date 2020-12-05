package io.github.blkmkt.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.blkmkt.order.entity.OrderEntity;
import io.github.blkmkt.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlkmktOrderApplicationTests {
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
//        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setId(1);
//        orderEntity.setUserId(1);
//        orderEntity.setGoodId(2);
//        orderService.save(orderEntity);
//        System.out.println("保存成功");

        List<OrderEntity> entities = orderService.list(new QueryWrapper<OrderEntity>().eq("id", 1));
        entities.forEach(System.out::println);
    }

}
