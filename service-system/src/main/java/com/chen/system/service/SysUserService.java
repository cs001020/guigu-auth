package com.chen.system.service;

import com.chen.model.po.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-28
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 选择页面
     *
     * @param pageParam      页面参数
     * @param sysUserQueryVo 系统用户查询签证官
     * @return {@link IPage}<{@link SysUser}>
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    /**
     * 更新状态
     *
     * @param id     id
     * @param status 状态
     * @return boolean
     */
    boolean updateStatus(String id, Integer status);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getUserInfo(String username);
}
