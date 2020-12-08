package io.github.blkmkt.forum.util;

import io.github.blkmkt.forum.entity.UserEntity;
import io.github.blkmkt.forum.service.PermissionService;
import io.github.blkmkt.forum.service.RolePerService;
import io.github.blkmkt.forum.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionUtil
{
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RolePerService rolePerService;
    @Autowired
    PermissionService permissionService;

    public boolean hasPermission(String permission, int uid){
        List<Integer> rlist = userRoleService.getRoleidByUserid(uid);
        List<Integer> plist = rolePerService.getPeridByRoleid(rlist.get(0));
        for(Integer i : plist){
            String temp = permissionService.getPermissionNameById(i);
            if(temp.equals(permission))return true;
        }
        return false;
    }
}
