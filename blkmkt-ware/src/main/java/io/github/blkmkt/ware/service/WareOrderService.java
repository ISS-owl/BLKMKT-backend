package io.github.blkmkt.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.blkmkt.ware.vo.WareLockOrderVo;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.ware.entity.WareOrderEntity;


/**
 * 商品订单信息
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-20 23:12:37
 */
public interface WareOrderService extends IService<WareOrderEntity> {

    PageUtils<WareOrderEntity> queryPage(PageParam params);

    Boolean orderLockStock(WareLockOrderVo wareLockOrderVo);
}

