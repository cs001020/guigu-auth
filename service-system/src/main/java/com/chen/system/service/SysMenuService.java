package com.chen.system.service;

import com.chen.model.po.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.model.vo.AssginMenuVo;
import com.chen.model.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 找到节点
     *
     * @return {@link List}<{@link SysMenu}>
     */
    List<SysMenu> findNodes();

    /**
     * 删除菜单通过id
     *
     * @param id id
     */
    void removeMenuById(String id);

    /**
     * 通过角色id找到菜单
     *
     * @param roleId 角色id
     * @return {@link List}<{@link SysMenu}>
     */
    List<SysMenu> findMenuByRoleId(String roleId);

    /**
     * 做分配
     *
     * @param assginMenuVo assgin菜单签证官
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 获取用户菜单列表
     *
     * @param id id
     * @return {@link List}<{@link RouterVo}>
     */
    List<RouterVo> getUserMenuList(String id);

    /**
     * 获取用户列表按钮
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> getUserButtonList(String id);
}
