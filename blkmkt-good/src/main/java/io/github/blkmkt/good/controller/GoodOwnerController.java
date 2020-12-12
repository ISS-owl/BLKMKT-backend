package io.github.blkmkt.good.controller;

import io.github.blkmkt.good.service.GoodService;
import io.github.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("good/good")
public class GoodOwnerController {
    @Autowired
    private GoodService goodService;

    @GetMapping("/owner_all_goods")
    public R getOwnerAllGoods(@RequestParam Integer ownerId) {
        return goodService.getOwnerAllGoods(ownerId);
    }

    @GetMapping("owner_up_goods")
    public R getOwnerUpGoods(@RequestParam Integer ownerId) {
        return goodService.getOwnerUpGoods(ownerId);
    }

    @GetMapping("owner_not_up_goods")
    public R getOwnerNotUpGoods(@RequestParam Integer ownerId) {
        return goodService.getOwnerNotUpGoods(ownerId);
    }
}
