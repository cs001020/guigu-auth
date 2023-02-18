package com.chen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.model.po.SysRole;
import com.chen.model.vo.AssginRoleVo;
import com.chen.model.vo.SysRoleQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 系统角色服务
 *
 * @author CHEN
 * @date 2023/02/10
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 条件分页查询
     *
     * @param pageParam      页面参数
     * @param sysRoleQueryVo 系统角色查询签证官
     * @return {@link IPage}<{@link SysRole}>
     */
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    /**
     * 被用户id角色
     *
     * @param userId 用户id
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getRolesByUserId(String userId);

    /**
     * 做分配
     *
     * @param assginRoleVo 签证官assgin作用
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
