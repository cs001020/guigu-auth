package com.chen.system.controller;

import com.chen.common.result.Result;
import com.chen.model.po.SysRole;
import com.chen.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统角色控制器
 *
 * @author CHEN
 * @date 2023/02/10
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有角色")
    @GetMapping("findAll")
    @Transactional
    public Result findAll(){
        return Result.ok(sysRoleService.list());
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") String id){
        boolean b = sysRoleService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.fail();
    }
}
