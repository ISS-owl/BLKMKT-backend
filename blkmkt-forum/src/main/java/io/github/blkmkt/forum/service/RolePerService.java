package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.RolePerEntity;

import java.util.List;

/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
public interface RolePerService extends IService<RolePerEntity> {

    PageUtils<RolePerEntity> queryPage(PageParam params);

    List<Integer> getPeridByRoleid(int rid);
}

