package io.github.blkmkt.forum.controller;

import java.util.Arrays;

import io.github.common.entity.PageParam;
import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import io.github.common.utils.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.blkmkt.forum.entity.RolePerEntity;
import io.github.blkmkt.forum.service.RolePerService;
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
@RequestMapping("order/roleper")
public class RolePerController {
    @Autowired
    private RolePerService rolePerService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "所有信息", notes = "根据分页参数（可缺省）获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "Number of pages"),
            @ApiImplicitParam(name = "pageSize", value = "Size of pages")
    })
    public ResponseWithData<PageUtils<RolePerEntity>> list(
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize", required = false) Long pageSize
    ){
        PageParam params = new PageParam(pageNo, pageSize, null, null);
        PageUtils<RolePerEntity> page = rolePerService.queryPage(params);

        return ResponseUtils.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "信息", notes = "获取指定id的信息")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public ResponseWithData<RolePerEntity> info(@PathVariable("roleid") Integer roleid){
		RolePerEntity rolePer = rolePerService.getById(roleid);

        return ResponseUtils.ok(rolePer);
    }

    /**
     * 保存
     */
    @PostMapping("/")
    @ApiOperation(value = "保存信息", notes = "保存信息")
    @ApiImplicitParam(name = "rolePer", value = "rolePer entity", required = true)
    public Response save(@RequestBody RolePerEntity rolePer){
		rolePerService.save(rolePer);

        return ResponseUtils.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/")
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @ApiImplicitParam(name = "rolePer", value = "rolePer entity", required = true)
    public Response update(@RequestBody RolePerEntity rolePer){
		rolePerService.updateById(rolePer);

        return ResponseUtils.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除", notes = "删除信息")
    @ApiImplicitParam(name = "roleids", value = "roleid array", required = true)
    public Response delete(@RequestBody Integer[] roleids){
		rolePerService.removeByIds(Arrays.asList(roleids));

        return ResponseUtils.ok();
    }

}
