package com.ruoyi.omd.service;

import java.util.List;
import com.ruoyi.omd.domain.OmdOrder;

/**
 * 订单信息Service接口
 *
 * @author loner
 * @date 2023-04-30
 */
public interface IOmdOrderService
{
    /**
     * 查询订单信息
     *
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    public OmdOrder selectOmdOrderByOrderId(Long orderId);

    /**
     * 查询订单信息列表
     *
     * @param omdOrder 订单信息
     * @return 订单信息集合
     */
    public List<OmdOrder> selectOmdOrderList(OmdOrder omdOrder);

    /**
     * 新增订单信息
     *
     * @param omdOrder 订单信息
     * @return 结果
     */
    public int insertOmdOrder(OmdOrder omdOrder);

    /**
     * 修改订单信息
     *
     * @param omdOrder 订单信息
     * @return 结果
     */
    public int updateOmdOrder(OmdOrder omdOrder);

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息主键集合
     * @return 结果
     */
    public int deleteOmdOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    public int deleteOmdOrderByOrderId(Long orderId);

    public List<OmdOrder> selectOrderAll();
}
