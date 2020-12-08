package io.github.blkmkt.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.PermissionDao;
import io.github.blkmkt.forum.entity.PermissionEntity;
import io.github.blkmkt.forum.service.PermissionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public PageUtils<PermissionEntity> queryPage(PageParam params) {
        IPage<PermissionEntity> page = this.page(
                new Query<PermissionEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public String getPermissionNameById(int pid)
    {
        QueryWrapper<PermissionEntity> queryWrapper = new QueryWrapper<PermissionEntity>();
        queryWrapper.select("name").eq("id",pid);
        List<PermissionEntity> list = permissionDao.selectList(queryWrapper);
        List<String> nList = list.stream().map(PermissionEntity::getName).collect(toList());
        return nList.get(0);
    }
}