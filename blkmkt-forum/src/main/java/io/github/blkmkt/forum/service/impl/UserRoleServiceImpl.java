package io.github.blkmkt.forum.service.impl;

import io.github.blkmkt.forum.entity.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.UserRoleDao;
import io.github.blkmkt.forum.entity.UserRoleEntity;
import io.github.blkmkt.forum.service.UserRoleService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public PageUtils<UserRoleEntity> queryPage(PageParam params) {
        IPage<UserRoleEntity> page = this.page(
                new Query<UserRoleEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public List<Integer> getRoleidByUserid(int uid)
    {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<UserRoleEntity>();
        queryWrapper.eq("userid",uid);
        List<UserRoleEntity> list = userRoleDao.selectList(queryWrapper);
        List<Integer> rList = list.stream().map(UserRoleEntity::getRoleid).collect(toList());
        return rList;
    }

}