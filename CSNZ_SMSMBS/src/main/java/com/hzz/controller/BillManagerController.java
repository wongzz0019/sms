package com.hzz.controller;
import com.alibaba.fastjson.JSONArray;
import com.hzz.pojo.Bill;
import com.hzz.pojo.Provider;
import com.hzz.pojo.User;
import com.hzz.service.BillService;
import com.hzz.service.ProviderService;
import com.hzz.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Bill")
public class BillManagerController {
    @Autowired
    private BillService billService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private PageSupport pageSupport;

    //进入订单管理页面
    @GetMapping("/toBillManagerPage")
    public String toBillManagerPage(String queryProductName, String queryProviderId, String queryIsPayment,String pageIndex, HttpServletRequest request){
        //设置分页默认参数
        int resProviderId = 0;
        int resIsPayment = 0;
        int pageSize = 5;           // 一页显式的数据量 默认为 5
        int currentPageNo = 1;      // 当前页码
        int totalCount = 0;         // 根据条件查询出来的记录总数量 默认为0
        int totalPageCount = 0;     // 全部数据量 算出 总共显示的页数

        //根据前端传来的信息做转化 替换默认分页信息
        if(queryProductName == null){
            queryProductName = "";
        }
        if(queryProviderId != null){
            resProviderId = Integer.parseInt(queryProviderId);
        }
        if(queryIsPayment != null ){
            resIsPayment = Integer.parseInt(queryIsPayment);
        }
        if(pageIndex != null){
            currentPageNo = Integer.parseInt(pageIndex);
        }

        //条件查询订单总数
        totalCount = billService.getBillCount(queryProductName, resProviderId, resIsPayment);
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

        //根据条件 查询订单列表
        List<Bill> billList = billService.getBillList(queryProductName, resProviderId, resIsPayment, currentPageNo, pageSize);
        request.setAttribute("billList",billList);

        //查询供应商列表
        int providerCounts = providerService.getProviderCounts("", "");
        List<Provider> providerList = providerService.getProviderList("", "", 1, providerCounts);
        request.setAttribute("providerList",providerList);

        //判断此 列表是否为空 是则 清零页码信息 并返回
        if(billList.size() == 0){
            request.setAttribute("queryProductName",queryProductName);
            request.setAttribute("queryProviderId",queryProviderId);
            request.setAttribute("queryIsPayment",queryIsPayment);
            request.setAttribute("totalPageCount",0);
            request.setAttribute("totalCount",0);
            request.setAttribute("currentPageNo",0);
            return "billlist";
        }

        //将所有信息返回至前端展示
        request.setAttribute("queryProductName",queryProductName);
        request.setAttribute("queryProviderId",queryProviderId);
        request.setAttribute("queryIsPayment",queryIsPayment);
        request.setAttribute("totalPageCount",totalPageCount);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("currentPageNo",currentPageNo);
        return "billlist";
    }

    //跳转到新添订单页面
    @GetMapping("/toAddBillPage")
    public String toAddBillPage(){
        return "billadd";
    }

    //新添订单
    @PostMapping("/addBill")
    public String addBill(Bill bill,HttpServletRequest request){
        //下面两个信息 是表单中没有的
        bill.setCreatedBy(((User)request.getSession().getAttribute("userSession")).getId());
        bill.setCreationDate(new Date());

        boolean flag = billService.addBill(bill);
        //如果新添成功 跳转回订单列表
        if(flag){
            return "redirect:/Bill/toBillManagerPage";
        }else{
            //新添失败 回到当前 添加页面
            return "billadd";
        }
    }

    //查看具体订单信息
    @GetMapping("/viewBill/{bid}")
    public String viewBill(@PathVariable String bid, HttpServletRequest request){
        // 根据订单id 查询 订单信息并返回至前端进行展示
        int id = Integer.parseInt(bid);
        Bill bill = billService.findBill(id);
        request.setAttribute("bill",bill);
        return "billview";
    }

    //去往 订单修改页面（顺带查询指定id订单信息）
    @GetMapping("/toModifyBillPage/{bid}")
    public String modifyBill(@PathVariable("bid") String billId,HttpServletRequest request){
        int id = Integer.parseInt(billId);
        Bill bill = billService.findBill(id);
        request.setAttribute("bill",bill);
        return "billmodify";
    }

    //修改订单信息
    @PostMapping("/modifyBill")
    public String modifyBill(String billid ,Bill bill,HttpServletRequest request){
        int id = Integer.parseInt(billid);
        bill.setModifyBy(((User)request.getSession().getAttribute("userSession")).getId());
        bill.setModifyDate(new Date());

        boolean flag = billService.updateBill(id, bill);
        if (flag) {
            return "redirect:/Bill/toBillManagerPage";
        }else{
            return "billmodify";
        }
    }
    //删除订单模块
    @GetMapping(value = "/deleteBill/{bid}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteBill(@PathVariable("bid") String billId){
        int id = Integer.parseInt(billId);
        HashMap<String, String> map = new HashMap<>();
        if (id <= 0) {
            map.put("delResult","notexist");
        }else{
            boolean flag = billService.deleteBill(id);
            if(flag){
                map.put("delResult","true");
            }else{
                map.put("delResult","false");
            }
        }
        return JSONArray.toJSONString(map);
    }
}
