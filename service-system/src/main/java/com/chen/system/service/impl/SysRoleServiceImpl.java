package com.chen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.model.po.SysRole;
import com.chen.system.mapper.SysRoleMapper;
import com.chen.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色服务impl
 *
 * @author CHEN
 * @date 2023/02/10
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
