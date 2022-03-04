package com.hzz.service.Impl;

import com.hzz.dao.Provider.ProviderMapper;
import com.hzz.pojo.Provider;
import com.hzz.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    //条件查询供应商总数
    @Override
    public int getProviderCounts(String queryProCode, String queryProName) {
        return providerMapper.getProviderCounts(queryProCode,queryProName);
    }

    //条件查询供应商数据列表 分页
    @Override
    public List<Provider> getProviderList(String queryProCode, String queryProName, int currentPageNo, int pageSize) {
        return providerMapper.getProviderList(queryProCode,queryProName,(currentPageNo-1)*pageSize,pageSize);
    }

    // 添加供应商
    @Override
    public boolean addProvider(Provider provider) {
        return providerMapper.addProvider(provider) == 1;
    }

    // 根据id 查询供应商信息
    @Override
    public Provider findById(int proId) {
        return providerMapper.findById(proId);
    }

    // 根据id 修改 供应商信息
    @Override
    public boolean updateProvider(int proId, Provider provider) {
        return providerMapper.updateProvider(proId,provider) == 1;
    }

    // 根据id 删除 供应商
    @Override
    public boolean deleteProvider(int proId) {
        return providerMapper.deleteProvider(proId) == 1;
    }
}
