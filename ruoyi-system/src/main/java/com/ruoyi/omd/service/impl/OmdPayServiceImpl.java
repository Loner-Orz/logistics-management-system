package com.ruoyi.omd.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.omd.mapper.OmdPayMapper;
import com.ruoyi.omd.domain.OmdPay;
import com.ruoyi.omd.service.IOmdPayService;

/**
 * 支付信息Service业务层处理
 *
 * @author loner
 * @date 2023-05-12
 */
@Service
public class OmdPayServiceImpl implements IOmdPayService
{
    @Autowired
    private OmdPayMapper omdPayMapper;

    /**
     * 查询支付信息
     *
     * @param payId 支付信息主键
     * @return 支付信息
     */
    @Override
    public OmdPay selectOmdPayByPayId(Long payId)
    {
        return omdPayMapper.selectOmdPayByPayId(payId);
    }

    /**
     * 查询支付信息列表
     *
     * @param omdPay 支付信息
     * @return 支付信息
     */
    @Override
    public List<OmdPay> selectOmdPayList(OmdPay omdPay)
    {
        return omdPayMapper.selectOmdPayList(omdPay);
    }

    /**
     * 新增支付信息
     *
     * @param omdPay 支付信息
     * @return 结果
     */
    @Override
    public int insertOmdPay(OmdPay omdPay)
    {
        omdPay.setCreateTime(DateUtils.getNowDate());
        return omdPayMapper.insertOmdPay(omdPay);
    }

    /**
     * 修改支付信息
     *
     * @param omdPay 支付信息
     * @return 结果
     */
    @Override
    public int updateOmdPay(OmdPay omdPay)
    {
        omdPay.setUpdateTime(DateUtils.getNowDate());
        return omdPayMapper.updateOmdPay(omdPay);
    }

    /**
     * 批量删除支付信息
     *
     * @param payIds 需要删除的支付信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdPayByPayIds(Long[] payIds)
    {
        return omdPayMapper.deleteOmdPayByPayIds(payIds);
    }

    /**
     * 删除支付信息信息
     *
     * @param payId 支付信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdPayByPayId(Long payId)
    {
        return omdPayMapper.deleteOmdPayByPayId(payId);
    }
}

