package com.hzz.dao.User;

import com.hzz.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//登录 判断 的接口

@Repository
public interface UserMapper {
    //根据userCode 查找用户信息
    public User findUserByUserCode(String userCode);


    //修改密码
    public int updatePassword(@Param("userId") int userId ,
                                       @Param("newPassword") String newPassword);

    //根据用户名 或 角色 查询用户总数
    public int getUserCounts(@Param("username") String username,
                                      @Param("userRole") int userRole);

    //根据条件 查询 获取用户列表 userlist
    public List<User> getUserList(@Param("username") String username,
                                           @Param("userRole") int userRole,
                                           @Param("currentPageNo") int currentPageNo,
                                           @Param("pageSize") int pageSize);



    //用户管理模块 —— 添加用户
    public abstract int addUser(User user);

    //用户管理模块 —— 删除用户
    public abstract boolean deleteUser(@Param("userId") int userId);

    //根据用户id 查询用户信息
    public abstract User findById(@Param("id") int userId);

    //用户管理模块 —— 更改用户信息
    public abstract boolean modify(@Param("id") int id,@Param("user") User user);
}
