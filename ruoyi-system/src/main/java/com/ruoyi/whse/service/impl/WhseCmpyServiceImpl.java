package com.ruoyi.whse.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.district.mapper.DistrictMapper;
import com.ruoyi.district.service.IDistrictService;
import com.ruoyi.whse.domain.WhseCmpy;
import com.ruoyi.whse.mapper.WhseCmpyMapper;
import com.ruoyi.whse.service.IWhseCmpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 往来公司信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class WhseCmpyServiceImpl implements IWhseCmpyService
{
    @Autowired
    private WhseCmpyMapper whseCmpyMapper;

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 查询往来公司信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 往来公司信息
     */
    @Override
    public WhseCmpy selectWhseCmpyByCmpyId(Long cmpyId)
    {
        return whseCmpyMapper.selectWhseCmpyByCmpyId(cmpyId);
    }

    /**
     * 查询往来公司信息列表
     *
     * @param whseCmpy 往来公司信息
     * @return 往来公司信息
     */
    @Override
    public List<WhseCmpy> selectWhseCmpyList(WhseCmpy whseCmpy)
    {
        return whseCmpyMapper.selectWhseCmpyList(whseCmpy);
    }

    /**
     * 新增往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    @Override
    public int insertWhseCmpy(WhseCmpy whseCmpy)
    {
        String provinceName = districtMapper.getNameByCode(whseCmpy.getProvinceCode());
        String cityName = districtMapper.getNameByCode(whseCmpy.getCityCode());
        String areaName = districtMapper.getNameByCode(whseCmpy.getAreaCode());
        whseCmpy.setProvinceName(provinceName);
        whseCmpy.setCityName(cityName);
        whseCmpy.setAreaName(areaName);
        whseCmpy.setCreateTime(DateUtils.getNowDate());
        return whseCmpyMapper.insertWhseCmpy(whseCmpy);
    }

    /**
     * 修改往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    @Override
    public int updateWhseCmpy(WhseCmpy whseCmpy)
    {
        String provinceName = districtMapper.getNameByCode(whseCmpy.getProvinceCode());
        String cityName = districtMapper.getNameByCode(whseCmpy.getCityCode());
        String areaName = districtMapper.getNameByCode(whseCmpy.getAreaCode());
        whseCmpy.setProvinceName(provinceName);
        whseCmpy.setCityName(cityName);
        whseCmpy.setAreaName(areaName);
        whseCmpy.setUpdateTime(DateUtils.getNowDate());
        //System.out.println(whseCmpy);
        return whseCmpyMapper.updateWhseCmpy(whseCmpy);
    }

    /**
     * 批量删除往来公司信息
     *
     * @param cmpyIds 需要删除的往来公司信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseCmpyByCmpyIds(Long[] cmpyIds)
    {
        return whseCmpyMapper.deleteWhseCmpyByCmpyIds(cmpyIds);
    }

    /**
     * 删除往来公司信息信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseCmpyByCmpyId(Long cmpyId)
    {
        return whseCmpyMapper.deleteWhseCmpyByCmpyId(cmpyId);
    }
}
