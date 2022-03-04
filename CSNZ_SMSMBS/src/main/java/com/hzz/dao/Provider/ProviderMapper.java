package com.hzz.dao.Provider;

import com.hzz.pojo.Provider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderMapper {
    // 根据供应商编码 或 供应商名称 查询供应商总数
    public int getProviderCounts(@Param("proCode") String queryProCode,@Param("proName") String queryProName);

    // 根据 供应商编码 和 供应商名称 和 当前页码 页码size 查询供应商数据列表
    public List<Provider> getProviderList(@Param("proCode") String queryProCode,@Param("proName") String queryProName,@Param("currentPageNo") int currentPageNo,@Param("pageSize") int pageSize);

    // 添加供应商
    public int addProvider(Provider provider);

    // 根据id 查询供应商信息
    public Provider findById(@Param("proId") int proId);


    // 根据id 修改 供应商信息
    public int updateProvider(@Param("proId") int proId, @Param("provider") Provider provider);

    // 根据id 删除 供应商
    public int deleteProvider(@Param("proId") int proId);


}
