package com.hzz.dao.Bill;
import com.hzz.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BillMapper {
    // 根据商品名称、供应商id、是否付款 查询订单总数
    public int getBillCount(@Param("productName") String queryProductName,
                                     @Param("providerId") int queryProviderId,
                                     @Param("isPayment") int queryIsPayment);

    // 根据商品名称、供应商id、是否付款 查询订单列表
    public List<Bill> getBillList(@Param("productName") String queryProductName,
                                           @Param("providerId")int queryProviderId,
                                           @Param("isPayment")int queryIsPayment,
                                           @Param("currentPageNo") int currentPageNo,
                                           @Param("pageSize") int pageSize);

    // 新添订单
    public int addBill(@Param("bill") Bill bill);

    // 根据 订单id 查询订单信息
    public Bill findBill(int billId);

    // 修改 订单信息
    public int updateBill(@Param("billId") int billId,@Param("bill") Bill bill);

    // 根据id 删除 订单
    public int deleteBill(@Param("billId") int billId);
}
