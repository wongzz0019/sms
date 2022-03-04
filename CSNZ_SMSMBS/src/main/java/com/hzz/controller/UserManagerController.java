package com.hzz.controller;

import com.alibaba.fastjson.JSONArray;
import com.hzz.pojo.Role;
import com.hzz.pojo.User;
import com.hzz.service.RoleService;
import com.hzz.service.UserService;
import com.hzz.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PageSupport pageSupport;


    //查询用户列表
    @GetMapping("/toUserManagerPage")
    public String toUserManagerPage(String queryName, String queryUserRole, String pageIndex, HttpServletRequest request) {
        //设置分页默认参数 还没根据前端传来的做转化
        int userRole = 0;       // 用户角色 默认 为 0
        int pageSize = 5;       // 一页显式的数据量 默认为 5
        int currentPageNo = 1;  // 当前页码
        int totalCount = 0;     // 根据条件查询出来的记录总数量 默认为0
        int totalPageCount = 0; // 全部数据量 算出 总共显示的页数

        //根据传来的参数做转化 替换默认分页信息
        if (queryUserRole != null) {
            userRole = Integer.parseInt(queryUserRole);
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }
        //按条件查询出来的总条数
        totalCount = userService.getUserCounts(queryName, userRole);

        pageSupport.setPageSize(pageSize);              // 设置 页面容量
        pageSupport.setTotalCount(totalCount);          // 设置 总数量(表)
        pageSupport.setCurrentPageNo(currentPageNo);    // 设置 当前页码 来自于用户输入
        //总共显示的页数
        totalPageCount = pageSupport.getTotalPageCount();

        //如果页数小于1，就显示第一页  页数大于 最后一页就 显示最后一页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        //查询用户列表
        List<User> userList = userService.getUserList(queryName, userRole, (currentPageNo - 1) * pageSize, pageSize);
        request.setAttribute("userList", userList);

        //判断此列表是否为空,是则清零页码信息 并返回
        if(userList.size() == 0){
            request.setAttribute("queryUserName", queryName);
            request.setAttribute("queryUserRole", queryUserRole);
            request.setAttribute("totalPageCount",0);
            request.setAttribute("totalCount",0);
            request.setAttribute("currentPageNo",0);
            return "userlist";
        }
        //查询用户角色列表
        List<Role> roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);
        request.setAttribute("queryUserName", queryName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        return "userlist";
    }

    //查询用户详情模块
    @GetMapping("/toViewUser/{uid}")
    public String toViewUser(@PathVariable String uid, HttpServletRequest request) {
        int id = Integer.parseInt(uid);
        User user = userService.findById(id);
        request.setAttribute("user", user);
        return "userview";
    }

    //修改用户详情模块
    @GetMapping("/toModifyUserPage/{uid}")
    public String toModifyUserPage(@PathVariable String uid, HttpServletRequest request) {
        int id = Integer.parseInt(uid);
        User user = userService.findById(id);
        request.setAttribute("user", user);
        System.out.println(user.toString());
        return "usermodify";
    }

    // 修改用户
    @PostMapping("/modifyUser")
    public String modifyUser(String uid, String userName, String gender, String birthday, String phone, String address, String userRole, HttpServletRequest request) throws ParseException {
        int id = Integer.parseInt(uid);
        User user = new User();
        user.setUserName(userName);
        user.setGender(Integer.parseInt(gender));
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.parseInt(userRole));
        //注意这两个参数不在表单的填写范围内
        user.setModifyBy(((User) request.getSession().getAttribute("userSession")).getId());
        user.setModifyDate(new Date());
        //修改
        boolean flag = userService.modify(id, user);
        //如果执行成功了 到 用户管理页面(即 查询全部用户列表)
        if (flag) {
            return "redirect:/UserManager/toUserManagerPage";
        }
        return "redirect:/UserManager/toModifyUserPage/" + id;
    }

    //删除用户模块
    @GetMapping(value = "/deleteUser/{uid}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUser(@PathVariable("uid") String id){
        int userId = Integer.parseInt(id);
        System.out.println("id ："+userId);
        HashMap<String, String> map = new HashMap<>();
        boolean flag = userService.deleteUser(userId);
        if (userId <= 0) {
            map.put("delResult","notexist");
        }else if(flag){
            map.put("delResult","true");
        }else{
            map.put("delResult","false");
        }
        return JSONArray.toJSONString(map);
    }

    //去往 添加用户信息的页面
    @GetMapping("/toAddUserPage")
    public String toAddUserPage() {
        return "useradd";
    }

    @PostMapping("/addUser")
    public String addUser(String userCode, String userName, String userPassword, String gender, String birthday, String phone, String address, String userRole,  HttpServletRequest request) throws ParseException {
        //转换格式后 调用service层 添加用户
        int resGender = Integer.parseInt(gender);
        int resUserRole = Integer.parseInt(userRole);
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(resGender);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(resUserRole);
        //注意这两个参数不在表单的填写范围内
        user.setCreatedBy(((User) request.getSession().getAttribute("userSession")).getId());
        user.setCreateDate(new Date());

        boolean flag = userService.addUser(user);
        if (flag) {
            return "redirect:/UserManager/toUserManagerPage";
        } else {
            return "useradd";
        }
    }

    //验证是否存在 此userCode
    @GetMapping(value = "/ifExistUserCode/{uCode}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ifExistUserCode(@PathVariable("uCode") String userCode){
        System.out.println("进入ifExistUserCode");
        //根据此 userCode 查询是否有这个用户
        boolean flag = userService.ifExistUserCode(userCode);
        HashMap<String, String> map = new HashMap<>();

        if(userCode==null || userCode.trim().equals("")){
            map.put("userCode","NoWrite");
        }else if(flag){
            map.put("userCode","exist");
        }
        return JSONArray.toJSONString(map);
    }


    //用户管理模块中 子模块(表单中的用户角色下拉框)
    @GetMapping(value = "/getRoleList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRoleList() {
        List<Role> roleList = roleService.getRoleList();
        //JSONArray 转换格式
        return JSONArray.toJSONString(roleList);
    }
}