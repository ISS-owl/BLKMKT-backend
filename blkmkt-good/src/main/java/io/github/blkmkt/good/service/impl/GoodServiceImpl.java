package io.github.blkmkt.good.service.impl;

import io.github.blkmkt.good.feign.ElasticSaveFeignService;
import io.github.blkmkt.good.vo.GoodModel;
import io.github.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.good.dao.GoodDao;
import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.service.GoodService;

import java.util.Collections;


@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodDao, GoodEntity> implements GoodService {
    @Autowired
    private ElasticSaveFeignService saveFeignService;

    @Override
    public PageUtils<GoodEntity> queryPage(PageParam params) {
        IPage<GoodEntity> page = this.page(
                new Query<GoodEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public R upGood(Integer id) {
        // 创建good model
        GoodEntity good = this.getById(id);
        return upGood(good);
    }

    @Override
    public R upGood(GoodEntity good) {
        GoodModel goodModel = new GoodModel();
        BeanUtils.copyProperties(good, goodModel);
        // 远程调用elasticsearch进行保存
        return saveFeignService.save(Collections.singletonList(goodModel));
    }

}
