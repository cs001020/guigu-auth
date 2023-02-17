package com.chen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.model.po.SysRole;
import com.chen.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 系统角色映射器
 *
 * @author CHEN
 * @date 2023/02/10
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 条件分页查询
     *
     * @param pageParam      页面参数
     * @param sysRoleQueryVo 系统角色查询签证官
     * @return {@link IPage}<{@link SysRole}>
     */
    IPage<SysRole> selectPage(Page<SysRole> pageParam, @Param("vo") SysRoleQueryVo sysRoleQueryVo);
}
