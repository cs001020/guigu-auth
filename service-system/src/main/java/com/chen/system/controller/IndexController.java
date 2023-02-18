package com.chen.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.common.result.Result;
import com.chen.common.utils.JwtHelper;
import com.chen.common.utils.MD5;
import com.chen.model.po.SysUser;
import com.chen.model.vo.LoginVo;
import com.chen.system.exception.ServiceException;
import com.chen.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Resource
    private SysUserService sysUserService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        //查询 用户
        LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,loginVo.getUsername());
        SysUser sysUser = sysUserService.getOne(wrapper);
        //判断
        if (Objects.isNull(sysUser)){
            throw new ServiceException("错误");
        }
        //比较密码
        String password = sysUser.getPassword();
        String encrypt = MD5.encrypt(password);
        if (password.equals(encrypt)) {
            throw new ServiceException("密码错误");
        }
        //判断状态
        if (sysUser.getStatus()==0){
            throw new ServiceException("账号停用");
        }
        //生成token
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String,Object> map=new HashMap<>(16);
        map.put("token",token);
        return Result.ok(map);
    }

    @ApiOperation("信息")
    @GetMapping("info")
    public Result info(HttpServletRequest request){
        //获取请求头token字符串
        String token = request.getHeader("token");

        //从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String,Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
