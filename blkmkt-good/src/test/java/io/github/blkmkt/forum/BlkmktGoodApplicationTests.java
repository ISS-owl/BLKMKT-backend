package io.github.blkmkt.forum;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.blkmkt.forum.entity.GoodEntity;
import io.github.blkmkt.forum.service.GoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlkmktGoodApplicationTests {
    @Autowired
    GoodService goodService;

    @Test
    void contextLoads() {
//        GoodEntity goodEntity = new GoodEntity();
//        goodEntity.setName("华为");
//        goodEntity.setCancelTime(new Date());
//        goodEntity.setCreateTime(new Date());
//        goodEntity.setCurrentNum(200);
//        goodEntity.setOwnerId(1);
//        goodEntity.setGoodImgUrl("baidu.com");
//        goodEntity.setPrice(200);
//        goodEntity.setTotalNum(200);
//        goodEntity.setGtype("666");
//
//        goodEntity.setName("华为手机");
//        goodService.save(goodEntity);
//        System.out.println("保存成功");

        GoodEntity entity = goodService.getOne(new QueryWrapper<GoodEntity>().eq("id", 1));
        System.out.println(entity);
    }

}
