package com.ruoyi.omd.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SnowflakeIdGenerator;
import com.ruoyi.district.mapper.DistrictMapper;
import com.ruoyi.omd.domain.OmdLogistics;
import com.ruoyi.omd.domain.OmdOrder;
import com.ruoyi.omd.domain.OmdPay;
import com.ruoyi.omd.mapper.OmdLogisticsMapper;
import com.ruoyi.omd.mapper.OmdOrderMapper;
import com.ruoyi.omd.mapper.OmdPayMapper;
import com.ruoyi.omd.service.IOmdOrderService;
import com.ruoyi.whse.domain.WhseComm;
import com.ruoyi.whse.domain.WhseStoreComm;
import com.ruoyi.whse.mapper.WhseCommMapper;
import com.ruoyi.whse.mapper.WhseStoreCommMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 订单信息Service业务层处理
 *
 * @author loner
 * @date 2023-04-30
 */
@Service
public class OmdOrderServiceImpl implements IOmdOrderService
{
    @Autowired
    public WhseStoreCommMapper whseStoreCommMapper;

    @Autowired
    private WhseCommMapper whseCommMapper;

    @Autowired
    private OmdPayMapper omdPayMapper;

    @Autowired
    private OmdLogisticsMapper omdLogisticsMapper;

    @Autowired
    private OmdOrderMapper omdOrderMapper;

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 查询订单信息
     *
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    @Override
    public OmdOrder selectOmdOrderByOrderId(Long orderId)
    {
        return omdOrderMapper.selectOmdOrderByOrderId(orderId);
    }

    /**
     * 查询订单信息列表
     *
     * @param omdOrder 订单信息
     * @return 订单信息
     */
    @Override
    public List<OmdOrder> selectOmdOrderList(OmdOrder omdOrder)
    {
        return omdOrderMapper.selectOmdOrderList(omdOrder);
    }

    /**
     * 新增订单信息
     *
     * @param omdOrder 订单信息
     * @return 结果
     */
    @Override
    public int insertOmdOrder(OmdOrder omdOrder)
    {
        //设置订单号
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,1);
        Long orderId = idGenerator.nextId();
        omdOrder.setOrderId(orderId);
        //设置省市区
        String provinceName = districtMapper.getNameByCode(omdOrder.getProvinceCode());
        String cityName = districtMapper.getNameByCode(omdOrder.getCityCode());
        String areaName = districtMapper.getNameByCode(omdOrder.getAreaCode());
        omdOrder.setProvinceName(provinceName);
        omdOrder.setCityName(cityName);
        omdOrder.setAreaName(areaName);
        //创建订单支付管理
        OmdPay omdPay = new OmdPay();
        Long payId = idGenerator.nextId();
        omdPay.setPayId(payId);//设置支付单号
        omdOrder.setOrderPid(String.valueOf(payId));//设置支付单号
        //设置支付金额
        WhseComm Comm = whseCommMapper.selectWhseCommByCommId(Long.valueOf(omdOrder.getOrderWid()));
        Long paySum = Comm.getCommPrice() * omdOrder.getOrderWcount();
        omdPay.setPaySum(paySum);
        //设置基础字段
        omdPay.setCreateBy(omdOrder.getCreateBy());
        omdPay.setCreateTime(DateUtils.getNowDate());

        omdPayMapper.insertOmdPay(omdPay);
        //创建订单物流管理
        OmdLogistics logistics = new OmdLogistics();
        Long logisticsId = idGenerator.nextId();
        logistics.setLogisticsId(logisticsId);
        omdOrder.setOrderLid(String.valueOf(logisticsId));
        //设置物流公司
        logistics.setLogisticsCmpy("极兔");
        //设置基础字段
        logistics.setCreateBy(omdOrder.getCreateBy());
        logistics.setCreateTime(DateUtils.getNowDate());

        omdLogisticsMapper.insertOmdLogistics(logistics);

        //减去商品数量
        WhseStoreComm storeComm = new WhseStoreComm();
        storeComm.setStoreId(Long.valueOf(omdOrder.getOrderSid()));
        storeComm.setCommId(Long.valueOf(omdOrder.getOrderWid()));
        Long commCount = whseStoreCommMapper.getCommCount(Long.valueOf(omdOrder.getOrderSid()),Long.valueOf(omdOrder.getOrderWid()));
        commCount -= omdOrder.getOrderWcount();
        storeComm.setCommCount(commCount);
        int i = whseStoreCommMapper.updateComm(storeComm);
        if(i <= 0){
            return 0;
        }

        omdOrder.setCreateTime(DateUtils.getNowDate());
        return omdOrderMapper.insertOmdOrder(omdOrder);
    }

    /**
     * 修改订单信息
     *
     * @param omdOrder 订单信息
     * @return 结果
     */
    @Override
    public int updateOmdOrder(OmdOrder omdOrder)
    {
        omdOrder.setUpdateTime(DateUtils.getNowDate());
        return omdOrderMapper.updateOmdOrder(omdOrder);
    }

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdOrderByOrderIds(Long[] orderIds)
    {
        for(int i =0 ; i < orderIds.length; i++){
            OmdOrder omdOrder = omdOrderMapper.selectOmdOrderByOrderId(orderIds[i]);
            omdPayMapper.deleteOmdPayByPayId(Long.valueOf(omdOrder.getOrderPid()));
            omdLogisticsMapper.deleteOmdLogisticsByLogisticsId(Long.valueOf(omdOrder.getOrderLid()));
        }
        return omdOrderMapper.deleteOmdOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdOrderByOrderId(Long orderId)
    {
        OmdOrder omdOrder = omdOrderMapper.selectOmdOrderByOrderId(orderId);
        omdPayMapper.deleteOmdPayByPayId(Long.valueOf(omdOrder.getOrderPid()));
        omdLogisticsMapper.deleteOmdLogisticsByLogisticsId(Long.valueOf(omdOrder.getOrderLid()));
        return omdOrderMapper.deleteOmdOrderByOrderId(orderId);
    }

    @Override
    public List<OmdOrder> selectOrderAll() {
        return omdOrderMapper.selectOrderAll();
    }
}
