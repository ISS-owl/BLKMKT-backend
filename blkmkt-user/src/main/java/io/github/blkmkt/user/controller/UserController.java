package io.github.blkmkt.user.controller;

import io.github.blkmkt.user.entity.UserEntity;
import io.github.blkmkt.user.service.UserService;
import io.github.blkmkt.user.vo.PasswordVo;
import io.github.common.entity.PageParam;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.PageUtils;
import io.github.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;


/**
 * 用户表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-07 19:59:03
 */
@Api(tags = {"用户"})
@RestController
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public R list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<UserEntity> page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);
		if (user != null) {
            // 禁止返回密码
            user.setPassword("");
            return R.ok().put("user", user);
        } else {
		    return R.error(BizCodeEnum.USER_NOT_EXIST.getCode(), BizCodeEnum.USER_NOT_EXIST.getMsg());
        }

    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "user", value = "user entity", required = true)
    public R save(@RequestBody UserEntity user){
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "user", value = "user entity", required = true)
    public R update(@RequestBody UserEntity user){
        user.setUpdateTime(new Date());
        if (user.getPassword() != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 更新密码
     */
    @PutMapping("/password")
    @ApiOperation(value = "更新密码", notes = "更新密码")
    @ApiImplicitParam(name = "passwordVo", value = "新旧密码实体", required = true)
    public R updatePassword(@RequestBody PasswordVo passwordVo) {
        UserEntity userEntity = this.userService.getById(passwordVo.getId());
        // 用户是否存在
        if (userEntity != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(passwordVo.getOldPassword(), userEntity.getPassword());
            // 旧密码和新密码是否匹配
            if (matches) {
                String encodePassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
                userEntity.setPassword(encodePassword);
                userService.updateById(userEntity);

                return R.ok();
            } else {
                return R.error(BizCodeEnum.USER_PASSWORD_NOT_CORRECT.getCode(), BizCodeEnum.USER_PASSWORD_NOT_CORRECT.getMsg());
            }
        } else {
            return R.error(BizCodeEnum.USER_NOT_EXIST.getCode(), BizCodeEnum.USER_NOT_EXIST.getMsg());
        }

    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public R delete(@RequestBody Integer[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
