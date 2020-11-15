package io.github.blkmkt.user;

import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BlkmktUserApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        UserEntity user = new UserEntity();
//        user.setAddress("666");
//        user.setCreateTime(new Date());
//        user.setDescription("666");
//        user.setEnabled(1);
//        user.setHeadImgUrl("baidu.com");
//        user.setMobile("666");
//        user.setName("zhihaoshen");
//        user.setPassword("123");
//        user.setSex(1);
//        user.setUpdateTime(new Date());
//        user.setStudentId("123456");
//        userService.save(user);
//        System.out.println("保存成功");
        UserEntity user = userService.getById(1);
        System.out.println(user);
    }

}
