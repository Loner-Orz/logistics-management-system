package com.ruoyi.omd.service;

import java.util.List;
import com.ruoyi.omd.domain.OmdPay;

/**
 * 支付信息Service接口
 *
 * @author loner
 * @date 2023-05-12
 */
public interface IOmdPayService
{
    /**
     * 查询支付信息
     *
     * @param payId 支付信息主键
     * @return 支付信息
     */
    public OmdPay selectOmdPayByPayId(Long payId);

    /**
     * 查询支付信息列表
     *
     * @param omdPay 支付信息
     * @return 支付信息集合
     */
    public List<OmdPay> selectOmdPayList(OmdPay omdPay);

    /**
     * 新增支付信息
     *
     * @param omdPay 支付信息
     * @return 结果
     */
    public int insertOmdPay(OmdPay omdPay);

    /**
     * 修改支付信息
     *
     * @param omdPay 支付信息
     * @return 结果
     */
    public int updateOmdPay(OmdPay omdPay);

    /**
     * 批量删除支付信息
     *
     * @param payIds 需要删除的支付信息主键集合
     * @return 结果
     */
    public int deleteOmdPayByPayIds(Long[] payIds);

    /**
     * 删除支付信息信息
     *
     * @param payId 支付信息主键
     * @return 结果
     */
    public int deleteOmdPayByPayId(Long payId);
}

