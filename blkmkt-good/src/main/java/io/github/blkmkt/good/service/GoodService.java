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

    PageUtils<GoodEntity> queryPage(PageParam params);

    R upGood(Integer id);

    R upGood(GoodEntity good);

    R updateGood(GoodEntity goodEntity);

    R deleteGood(List<Integer> ids);

    List<GoodEntity> getOwnerAllGoods(Integer ownerId);

    List<GoodEntity> getOwnerUpGoods(Integer ownerId);

    List<GoodEntity> getOwnerNotUpGoods(Integer ownerId);
}

