package io.github.blkmkt.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.to.OrderTo;
import io.github.common.to.StockLockedTo;
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

    void orderLockStock(WareLockOrderVo wareLockOrderVo);

    void unlock(StockLockedTo stockLockedTo);

    void unlock(OrderTo orderTo);
}

