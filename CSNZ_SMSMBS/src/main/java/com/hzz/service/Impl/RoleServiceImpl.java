package com.hzz.service.Impl;

import com.hzz.dao.Role.RoleMapper;
import com.hzz.pojo.Role;
import com.hzz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
