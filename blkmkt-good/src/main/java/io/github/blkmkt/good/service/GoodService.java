package io.github.blkmkt.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.good.entity.GoodEntity;

import java.util.Map;

/**
 * 货物表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-11-15 21:00:52
 */
public interface GoodService extends IService<GoodEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

