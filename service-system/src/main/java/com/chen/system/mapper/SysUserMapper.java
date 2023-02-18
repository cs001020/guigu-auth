package com.chen.system.mapper;

import com.chen.model.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-09-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 选择页面
     *
     * @param pageParam      页面参数
     * @param sysUserQueryVo 系统用户查询签证官
     * @return {@link IPage}<{@link SysUser}>
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);

}
