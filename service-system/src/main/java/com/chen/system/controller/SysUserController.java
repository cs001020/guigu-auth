package com.chen.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.common.result.Result;
import com.chen.common.utils.MD5;
import com.chen.model.po.SysUser;
import com.chen.model.vo.SysUserQueryVo;
import com.chen.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统用户控制器
 *
 * @author CHEN
 * @date 2023/02/17
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Resource
    private SysUserService service;

    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo) {
        //创建page对象
        Page<SysUser> pageParam = new Page<>(page, limit);
        //调用service方法
        IPage<SysUser> pageModel = service.selectPage(pageParam, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser){
        String encrypt = MD5.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);
        boolean success = service.save(sysUser);
        if (success){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("根据id查询")
    @GetMapping("/getUser/{id}")
    public Result getUserById(@PathVariable Long id){
        SysUser sysUser = service.getById(id);
        return Result.ok(sysUser);
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public Result update(@RequestBody SysUser sysUser){
        boolean success = service.updateById(sysUser);
        if (success){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean success = service.removeById(id);
        if (success){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("更改用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id, @PathVariable Integer status){
        boolean success = service.updateStatus(id, status);
        if (success){
            return Result.ok();
        }
        return Result.fail();
    }


}
