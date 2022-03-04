package com.hzz.service.Impl;

import com.hzz.dao.User.UserMapper;
import com.hzz.pojo.User;
import com.hzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userCode, String password) {
        //先通过 userCode查找是否有用户的信息 再判断用户密码是否相等 如果不相同就返回 null
        User user = userMapper.findUserByUserCode(userCode);
        boolean flag =  user != null ? user.getUserPassword().equals(password) : false;
        return flag == true ? user : null;
    }

    @Override
    public boolean updatePassword(int id, String passWord) {
        return userMapper.updatePassword(id,passWord)==1;
    }

    //根据用户 userCode 查询是否拥有这个用户
    @Override
    public boolean ifExistUserCode(String userCode) {
        return userMapper.findUserByUserCode(userCode)!=null;
    }


    @Override
    public int getUserCounts(String username, int userRole) {
        return userMapper.getUserCounts(username,userRole);
    }

    @Override
    public List<User> getUserList(String QueryUserName, int QueryUserRole, int currentPageNo, int pageSize) {
        return userMapper.getUserList(QueryUserName,QueryUserRole,currentPageNo,pageSize);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user)==1;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public User findById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public boolean modify(int id, User user) {
        return userMapper.modify(id,user);
    }
}
