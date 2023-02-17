package com.chen.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.common.result.Result;
import com.chen.model.po.SysRole;
import com.chen.model.vo.SysRoleQueryVo;
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
    public Result findAll() {
        return Result.ok(sysRoleService.list());
    }

    @ApiOperation("根据id删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") String id) {
        boolean b = sysRoleService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{size}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long size,
                                    SysRoleQueryVo sysRoleQueryVo) {

        Page<SysRole> pageParam = new Page<>(page, size);
        IPage<SysRole> sysRoleIPage = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(sysRoleIPage);
    }

    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result savaRole(@RequestBody SysRole sysRole) {
        boolean save = sysRoleService.save(sysRole);
        if (save) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("根据id查询")
    @PostMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        return Result.ok(sysRoleService.getById(id));
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public Result update(@RequestBody SysRole sysRole) {
        boolean success = sysRoleService.updateById(sysRole);
        if (success) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        boolean success = sysRoleService.removeBatchByIds(ids);
        if (success) {
            return Result.ok();
        }
        return Result.fail();
    }
}
