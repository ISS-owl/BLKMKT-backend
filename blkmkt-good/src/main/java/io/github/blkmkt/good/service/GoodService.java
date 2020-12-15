package io.github.blkmkt.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.good.entity.GoodEntity;
import io.github.common.utils.R;

import java.util.List;


/**
 * 货物表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 16:12:43
 */
public interface GoodService extends IService<GoodEntity> {

    R upGood(Integer id);

    R upGood(GoodEntity good);

    R updateGood(GoodEntity goodEntity);

    R deleteGood(List<Integer> ids);

    PageUtils<GoodEntity> queryPage(PageParam params);

    PageUtils<GoodEntity> getOwnerAllGoods(PageParam param, Integer ownerId);

    PageUtils<GoodEntity> getOwnerUpGoods(PageParam param, Integer ownerId);

    PageUtils<GoodEntity> getOwnerNotUpGoods(PageParam param, Integer ownerId);
}

