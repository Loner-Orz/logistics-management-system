package com.ruoyi.whse.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.district.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.whse.mapper.WhseStoreMapper;
import com.ruoyi.whse.domain.WhseStore;
import com.ruoyi.whse.service.IWhseStoreService;

/**
 * 仓库信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class WhseStoreServiceImpl implements IWhseStoreService
{
    @Autowired
    private WhseStoreMapper whseStoreMapper;

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 查询仓库信息
     *
     * @param storeId 仓库信息主键
     * @return 仓库信息
     */
    @Override
    public WhseStore selectWhseStoreByStoreId(Long storeId)
    {
        return whseStoreMapper.selectWhseStoreByStoreId(storeId);
    }

    /**
     * 查询仓库信息列表
     *
     * @param whseStore 仓库信息
     * @return 仓库信息
     */
    @Override
    public List<WhseStore> selectWhseStoreList(WhseStore whseStore)
    {
        return whseStoreMapper.selectWhseStoreList(whseStore);
    }

    /**
     * 新增仓库信息
     *
     * @param whseStore 仓库信息
     * @return 结果
     */
    @Override
    public int insertWhseStore(WhseStore whseStore)
    {
        String provinceName = districtMapper.getNameByCode(whseStore.getProvinceCode());
        String cityName = districtMapper.getNameByCode(whseStore.getCityCode());
        String areaName = districtMapper.getNameByCode(whseStore.getAreaCode());
        whseStore.setProvinceName(provinceName);
        whseStore.setCityName(cityName);
        whseStore.setAreaName(areaName);
        whseStore.setCreateTime(DateUtils.getNowDate());
        return whseStoreMapper.insertWhseStore(whseStore);
    }

    /**
     * 修改仓库信息
     *
     * @param whseStore 仓库信息
     * @return 结果
     */
    @Override
    public int updateWhseStore(WhseStore whseStore)
    {
        String provinceName = districtMapper.getNameByCode(whseStore.getProvinceCode());
        String cityName = districtMapper.getNameByCode(whseStore.getCityCode());
        String areaName = districtMapper.getNameByCode(whseStore.getAreaCode());
        whseStore.setProvinceName(provinceName);
        whseStore.setCityName(cityName);
        whseStore.setAreaName(areaName);
        whseStore.setUpdateTime(DateUtils.getNowDate());
        return whseStoreMapper.updateWhseStore(whseStore);
    }

    /**
     * 批量删除仓库信息
     *
     * @param storeIds 需要删除的仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseStoreByStoreIds(Long[] storeIds)
    {
        return whseStoreMapper.deleteWhseStoreByStoreIds(storeIds);
    }

    /**
     * 删除仓库信息信息
     *
     * @param storeId 仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseStoreByStoreId(Long storeId)
    {
        return whseStoreMapper.deleteWhseStoreByStoreId(storeId);
    }

    /**
     * 根据所有仓库信息
     *
     * @return 仓库集合信息
     */
    @Override
    public List<WhseStore> selectWhseStoreAll() {
        return whseStoreMapper.selectWhseStoreAll();
    }
}
