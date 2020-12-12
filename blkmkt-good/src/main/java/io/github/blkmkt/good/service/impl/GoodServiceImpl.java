package io.github.blkmkt.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.blkmkt.good.dao.GoodDao;
import io.github.blkmkt.good.entity.GoodEntity;
import io.github.blkmkt.good.feign.ElasticFeignService;
import io.github.blkmkt.good.service.GoodService;
import io.github.blkmkt.good.vo.GoodModel;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodDao, GoodEntity> implements GoodService {
    @Autowired
    private ElasticFeignService elasticFeignService;

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
        // 不同的属性
        Integer hasStock = good.getCurrentNum() > 0? 1 : 0;
        goodModel.setHasStock(hasStock);
        // 远程调用elasticsearch进行保存
        return elasticFeignService.save(Collections.singletonList(goodModel));
    }

    @Override
    public R updateGood(GoodEntity goodEntity) {
        GoodModel goodModel = new GoodModel();
        BeanUtils.copyProperties(goodEntity, goodModel);

        Integer hasStock = goodEntity.getCurrentNum() > 0? 1 : 0;
        goodModel.setHasStock(hasStock);
        goodModel.setUpdateTime(new Date());
        return elasticFeignService.update(Collections.singletonList(goodModel));
    }

    @Override
    public R deleteGood(List<Integer> ids) {
        return elasticFeignService.delete(ids);
    }

    @Override
    public R getOwnerAllGoods(Integer ownerId) {
        List<GoodEntity> goods = this.baseMapper.selectList(new QueryWrapper<GoodEntity>().eq("owner_id", ownerId));
        return R.ok().put("data", goods);
    }

    @Override
    public R getOwnerUpGoods(Integer ownerId) {
        List<GoodEntity> goods = this.baseMapper.selectList(new QueryWrapper<GoodEntity>().eq("owner_id", ownerId).eq("status", 1));
        return R.ok().put("data", goods);
    }

    @Override
    public R getOwnerNotUpGoods(Integer ownerId) {
        List<GoodEntity> goods = this.baseMapper.selectList(new QueryWrapper<GoodEntity>().eq("owner_id", ownerId).eq("status", 0));
        return R.ok().put("data", goods);
    }

}
