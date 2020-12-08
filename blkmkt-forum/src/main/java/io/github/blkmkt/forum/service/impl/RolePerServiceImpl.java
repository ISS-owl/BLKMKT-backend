package io.github.blkmkt.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.RolePerDao;
import io.github.blkmkt.forum.entity.RolePerEntity;
import io.github.blkmkt.forum.service.RolePerService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("rolePerService")
public class RolePerServiceImpl extends ServiceImpl<RolePerDao, RolePerEntity> implements RolePerService {

    @Autowired
    RolePerDao rolePerDao;

    @Override
    public PageUtils<RolePerEntity> queryPage(PageParam params) {
        IPage<RolePerEntity> page = this.page(
                new Query<RolePerEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    public List<Integer> getPeridByRoleid(int rid){
        QueryWrapper<RolePerEntity> queryWrapper = new QueryWrapper<RolePerEntity>();
        queryWrapper.eq("roleid",rid);
        List<RolePerEntity> list = rolePerDao.selectList(queryWrapper);
        List<Integer> pList = list.stream().map(RolePerEntity::getPerid).collect(toList());
        return pList;
    }
}