package com.chen.system.controller;

import com.chen.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 指数控制器
 *
 * @author CHEN
 * @date 2023/02/16
 */
@Api(tags = "登陆")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result login(){
        Map<String,Object> map=new HashMap<>(16);
        map.put("token","admin-token");
        return Result.ok(map);
    }

    @ApiOperation("信息")
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map=new HashMap<>(16);
        map.put("roles", Collections.singletonList("admin"));
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin cs");
        return Result.ok(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
