package com.chen.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.model.po.SysMenu;
import com.chen.model.po.SysRoleMenu;
import com.chen.model.vo.AssginMenuVo;
import com.chen.model.vo.RouterVo;
import com.chen.system.exception.ServiceException;
import com.chen.system.mapper.SysMenuMapper;
import com.chen.system.mapper.SysRoleMapper;
import com.chen.system.mapper.SysRoleMenuMapper;
import com.chen.system.service.SysMenuService;
import com.chen.system.utils.MenuHelper;
import com.chen.system.utils.RouterHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> list = list();
        return MenuHelper.bulidTree(list);
    }

    @Override
    public void removeMenuById(String id) {
        //查询子菜单
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysMenu::getParentId,id);
        long count = count(lambdaQueryWrapper);
        if (count>0){
            throw new ServiceException("无法删除");
        }
        removeById(id);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        LambdaQueryWrapper<SysMenu> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getStatus,1);
        //获取所有
        List<SysMenu> list = list(queryWrapper);
        //关系表
        LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRoleMenu::getRoleId,roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(lambdaQueryWrapper);
        List<String> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        //是否选择
        list.forEach(sysMenu -> {
            if (menuIds.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        });
        //树化 返回
        return MenuHelper.bulidTree(list);
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除
        LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRoleMenu::getRoleId,assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(lambdaQueryWrapper);
        //重建
        assginMenuVo.getMenuIdList().forEach(id->{
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenu.setMenuId(id);
            sysRoleMenuMapper.insert(sysRoleMenu);
        });
    }

    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        //admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        //判断userid值是1代表超级管理员，查询所有权限数据
        if("1".equals(userId)) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            //如果userid不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }

        //构建是树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.bulidTree(sysMenuList);

        //转换成前端路由要求格式数据
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);
        return routerVoList;
    }

    @Override
    public List<String> getUserButtonList(String userId) {
        List<SysMenu> sysMenuList = null;
        //判断是否管理员
        if("1".equals(userId)) {
            sysMenuList =
                    baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        //sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu:sysMenuList) {
            // type=2
            if(sysMenu.getType()==2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }
}
