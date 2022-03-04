package com.hzz.service.Impl;
import com.hzz.dao.Bill.BillMapper;
import com.hzz.pojo.Bill;
import com.hzz.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    //条件查询订单总数
    @Override
    public int getBillCount(String queryProductName, int queryProviderId, int queryIsPayment) {
        return billMapper.getBillCount(queryProductName,queryProviderId,queryIsPayment);
    }

    //条件查询订单列表 分页
    @Override
    public List<Bill> getBillList(String queryProductName, int queryProviderId, int queryIsPayment, int currentPageNo, int pageSize) {
        return billMapper.getBillList(queryProductName,queryProviderId,queryIsPayment,(currentPageNo-1)*pageSize,pageSize);
    }

    // 新添订单
    @Override
    public boolean addBill(Bill bill) {
        return billMapper.addBill(bill) == 1;
    }

    // 根据 订单id 查询订单信息
    @Override
    public Bill findBill(int billId) {
        return billMapper.findBill(billId);
    }
    // 修改 订单信息
    @Override
    public boolean updateBill(int billId, Bill bill) {
        return billMapper.updateBill(billId,bill) == 1;
    }
    // 根据id 删除 订单
    @Override
    public boolean deleteBill(int billId) {
        return billMapper.deleteBill(billId) == 1;
    }

}

