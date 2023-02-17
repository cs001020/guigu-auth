package com.chen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.model.po.SysRole;
import com.chen.model.vo.SysRoleQueryVo;

import java.util.List;

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
}
