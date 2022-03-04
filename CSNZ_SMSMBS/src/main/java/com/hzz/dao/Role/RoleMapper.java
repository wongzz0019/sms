package com.hzz.dao.Role;

import com.hzz.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleMapper {
    // 获取用户角色列表
    public List<Role> getRoleList();
}
