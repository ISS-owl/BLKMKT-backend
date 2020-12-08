package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.UserRoleEntity;

import java.util.List;

/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils<UserRoleEntity> queryPage(PageParam params);

    List<Integer> getRoleidByUserid(int uid);
}

