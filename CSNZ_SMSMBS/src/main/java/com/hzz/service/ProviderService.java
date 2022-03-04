package com.hzz.service;

import com.hzz.pojo.Provider;

import java.util.List;

public interface ProviderService {

    // 根据供应商编码 或 供应商名称 查询供应商总数
    public int getProviderCounts(String queryProCode,String queryProName);

    // 根据 供应商编码 和 供应商名称 和 当前页码 页码size 查询供应商数据列表
    public List<Provider> getProviderList(String queryProCode, String queryProName, int currentPageNo, int pageSize);

    // 添加供应商
    public boolean addProvider(Provider provider);

    // 根据id 查询供应商信息
    public Provider findById(int proId);

    // 根据id 修改 供应商信息
    public boolean updateProvider(int proId,Provider provider);

    // 根据id 删除 供应商
    public boolean deleteProvider(int proId);
}
