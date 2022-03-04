package com.hzz.controller;

import com.alibaba.fastjson.JSONArray;
import com.hzz.pojo.Provider;
import com.hzz.pojo.User;
import com.hzz.service.ProviderService;
import com.hzz.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Provider")
public class ProviderManagerController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private PageSupport pageSupport;

    //查看供应商列表模块
    @GetMapping("/toProviderPage")
    public String toProviderPage(String queryProCode , String queryProName , String pageIndex , HttpServletRequest request){
        //设置分页默认参数
        int pageSize = 5;           // 一页显式的数据量 默认为 5
        int currentPageNo = 1;      // 当前页码
        int totalCount = 0;         // 根据条件查询出来的记录总数量 默认为0
        int totalPageCount = 0;     // 全部数据量 算出 总共显示的页数

        if(pageIndex != null){
            currentPageNo = Integer.parseInt(pageIndex);
        }
        totalCount = providerService.getProviderCounts(queryProCode,queryProName);

        //设置pageSupport的参数
        pageSupport.setPageSize(pageSize);              // 设置 页面容量
        pageSupport.setTotalCount(totalCount);          // 设置 总数量(表)
        pageSupport.setCurrentPageNo(currentPageNo);    // 设置 当前页码 来自于用户输入
        //总共显示的页数
        totalPageCount = pageSupport.getTotalPageCount();

        if(currentPageNo<1){
            currentPageNo =1;
        }else if(currentPageNo>totalPageCount){
            currentPageNo = totalPageCount;
        }

        //根据条件 查询供应商列表
        List<Provider> providerList = providerService.getProviderList(queryProCode, queryProName, currentPageNo, pageSize);
        request.setAttribute("providerList",providerList);

        //判断此 列表是否为空 是则 清零页码信息 并返回
        if(providerList.size() == 0){
            System.out.println("\n潮汕奴仔_优化\n");
            request.setAttribute("queryProCode",queryProCode);
            request.setAttribute("queryProName",queryProName);
            request.setAttribute("totalPageCount",0);
            request.setAttribute("totalCount",0);
            request.setAttribute("currentPageNo",0);
            return "providerlist";
        }

        request.setAttribute("queryProCode",queryProCode);
        request.setAttribute("queryProName",queryProName);
        request.setAttribute("totalPageCount",totalPageCount);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("currentPageNo",currentPageNo);
        return "providerlist";
    }

    // 去往 添加供应商 页面
    @GetMapping("/toAddProviderPage")
    public String toAddProviderPage(){
        return "provideradd";
    }

    // 添加供应商
    @PostMapping("/addProvider")
    public String addProvider(Provider provider,HttpServletRequest request){
        provider.setCreatedBy(((User)request.getSession().getAttribute("userSession")).getId());
        provider.setCreationDate(new Date());
        //调用service进行添加供应商 返回结果
        boolean flag = providerService.addProvider(provider);
        if(flag){
            return "redirect:/Provider/toProviderPage";
        }
            return "provideradd";
    }

    // 查看供应商信息模块
    @GetMapping("/viewProvider/{proId}")
    public ModelAndView viewProvider(@PathVariable String proId, ModelAndView modelAndView){
        // 转换前端传来的id
        int id = Integer.parseInt(proId);
        // 根据id查询供应商信息
        Provider provider = providerService.findById(id);

        modelAndView.addObject("provider",provider);
        modelAndView.setViewName("providerview");
        return modelAndView;
    }

    // 跳转到 供应商修改页面 并查询供应商信息
    @GetMapping("/toModifyProviderPage/{proId}")
    public String toModifyProviderPage(@PathVariable String proId,HttpServletRequest request){
        int id =  Integer.parseInt(proId);
        Provider provider = providerService.findById(id);
        request.setAttribute("provider",provider);
        return "providermodify";
    }

    // 修改供应商信息
    @PostMapping("/modifyProvider")
    public String modifyProvider(String proid , Provider provider,HttpServletRequest request){
        int id = Integer.parseInt(proid);
        provider.setModifyBy(((User)request.getSession().getAttribute("userSession")).getId());
        provider.setModifyDate(new Date());
        // 将前端传来的 provider 的信息 update到数据库
        boolean flag = providerService.updateProvider(id, provider);
        if(flag){
            return "redirect:/Provider/toProviderPage";
        }else{
            return "providermodify";
        }
    }


    // 删除供应商模块
    @GetMapping(value = "/deleteProvider/{proId}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteProvider(@PathVariable String proId){
        // 将前端传来的值 进行转换
        int id = Integer.parseInt(proId);
        HashMap<String, String> map = new HashMap<>();
        if (id <= 0) {
            map.put("delResult","notexist");
        }else{
            //删除指定id 的供应商
            boolean flag = providerService.deleteProvider(id);
            if(flag){
                map.put("delResult","true");
            }else{
                map.put("delResult","false");
            }
        }
        return JSONArray.toJSONString(map);
    }


    // 订单 添加模块 需要查询供应商列表进行展示
    @GetMapping(value = "/getProviderList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getProviderList(){
        int providerCounts = providerService.getProviderCounts("", "");
        List<Provider> providerList = providerService.getProviderList("", "", 1, providerCounts);
        return JSONArray.toJSONString(providerList);
    }
}
