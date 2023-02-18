package com.chen.system.mapper;

import com.chen.model.po.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 找到菜单列表用户id
     *
     * @param userId 用户id
     * @return {@link List}<{@link SysMenu}>
     */
    List<SysMenu> findMenuListUserId(@Param("userId") String userId);

    //根据userid查找菜单权限数据
}
