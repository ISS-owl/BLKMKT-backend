package io.github.blkmkt.forum.controller;

import java.util.Arrays;
import java.util.List;

import io.github.common.entity.PageParam;
import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import io.github.common.utils.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.PermissionEntity;
import io.github.blkmkt.forum.service.PermissionService;
import io.github.common.utils.PageUtils;

import static java.util.stream.Collectors.toList;

/**
 * 
 *
 * @author Yuniing Wu
 * @email
 * @date 2020-12-08 11:32:12
 */
@Api(tags = {""})
@RestController
@RequestMapping("order/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public ResponseWithData<PageUtils<PermissionEntity>> list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<PermissionEntity> page = permissionService.queryPage(params);

        return ResponseUtils.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public ResponseWithData<PermissionEntity> info(@PathVariable("id") Integer id){
		PermissionEntity permission = permissionService.getById(id);

        return ResponseUtils.ok(permission);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "permission", value = "permission entity", required = true)
    public Response save(@RequestBody PermissionEntity permission){
		permissionService.save(permission);

        return ResponseUtils.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "permission", value = "permission entity", required = true)
    public Response update(@RequestBody PermissionEntity permission){
		permissionService.updateById(permission);

        return ResponseUtils.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "ids", value = "id array", required = true)
    public Response delete(@RequestBody Integer[] ids){
		permissionService.removeByIds(Arrays.asList(ids));

        return ResponseUtils.ok();
    }


}
