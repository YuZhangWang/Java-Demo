package com.flower.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flower.entity.SysPermission;
import java.util.List;

/**
 * Created by yumaoying on 2018/4/22.
 * 权限Service
 */
public interface SysPermissionService {

    //查询权限信息
    public List<SysPermission> findByParentIdAfterOrderBySortAsc(Integer pid);

    //分页查询
    public Page<SysPermission> getPermissionPages(SysPermission permission, Pageable pageable);

    //添加权限
    public void add(SysPermission permission);

    //修改权限
    public void edit(SysPermission permission);

    public void delete(Integer id);

    public SysPermission findById(Integer id);
}
