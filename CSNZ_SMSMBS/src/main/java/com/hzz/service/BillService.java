package com.hzz.service;

import com.hzz.pojo.Bill;

import java.util.List;

public interface BillService {
    //根据商品名称、供应商id、是否付款 查询订单总数
    public int getBillCount(String queryProductName,int queryProviderId,int queryIsPayment);

    //根据商品名称、供应商id、是否付款 查询订单列表
    public List<Bill> getBillList(String queryProductName, int queryProviderId, int queryIsPayment, int currentPageNo, int pageSize);

    // 新添订单
    public boolean addBill(Bill bill);

    // 根据 订单id 查询订单信息
    public Bill findBill(int billId);

    // 修改 订单信息
    public boolean updateBill(int billId,Bill bill);

    // 根据id 删除 订单
    public boolean deleteBill(int billId);
}
