package io.github.blkmkt.forum.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import io.github.common.utils.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.RoleEntity;
import io.github.blkmkt.forum.service.RoleService;
import io.github.common.utils.PageUtils;



/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
@Api(tags = {""})
@RestController
@RequestMapping("order/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public ResponseWithData<PageUtils<RoleEntity>> list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<RoleEntity> page = roleService.queryPage(params);

        return ResponseUtils.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public ResponseWithData<RoleEntity> info(@PathVariable("id") Integer id){
		RoleEntity role = roleService.getById(id);

        return ResponseUtils.ok(role);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "role", value = "role entity", required = true)
    public Response save(@RequestBody RoleEntity role){
		roleService.save(role);

        return ResponseUtils.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "role", value = "role entity", required = true)
    public Response update(@RequestBody RoleEntity role){
		roleService.updateById(role);

        return ResponseUtils.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public Response delete(@RequestBody Integer[] ids){
		roleService.removeByIds(Arrays.asList(ids));

        return ResponseUtils.ok();
    }

}
