package io.github.blkmkt.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.ware.entity.WareGoodEntity;


/**
 * 商品库存表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
public interface WareGoodService extends IService<WareGoodEntity> {

    PageUtils<WareGoodEntity> queryPage(PageParam params);
}

