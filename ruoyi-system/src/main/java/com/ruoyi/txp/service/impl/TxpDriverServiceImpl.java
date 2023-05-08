package com.ruoyi.txp.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.txp.mapper.TxpDriverMapper;
import com.ruoyi.txp.domain.TxpDriver;
import com.ruoyi.txp.service.ITxpDriverService;

/**
 * 驾驶员信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-17
 */
@Service
public class TxpDriverServiceImpl implements ITxpDriverService
{
    @Autowired
    private TxpDriverMapper txpDriverMapper;

    /**
     * 查询驾驶员信息
     *
     * @param driverId 驾驶员信息主键
     * @return 驾驶员信息
     */
    @Override
    public TxpDriver selectTxpDriverByDriverId(Long driverId)
    {
        return txpDriverMapper.selectTxpDriverByDriverId(driverId);
    }

    /**
     * 查询驾驶员信息列表
     *
     * @param txpDriver 驾驶员信息
     * @return 驾驶员信息
     */
    @Override
    public List<TxpDriver> selectTxpDriverList(TxpDriver txpDriver)
    {
        return txpDriverMapper.selectTxpDriverList(txpDriver);
    }

    /**
     * 新增驾驶员信息
     *
     * @param txpDriver 驾驶员信息
     * @return 结果
     */
    @Override
    public int insertTxpDriver(TxpDriver txpDriver)
    {
        txpDriver.setCreateTime(DateUtils.getNowDate());
        return txpDriverMapper.insertTxpDriver(txpDriver);
    }

    /**
     * 修改驾驶员信息
     *
     * @param txpDriver 驾驶员信息
     * @return 结果
     */
    @Override
    public int updateTxpDriver(TxpDriver txpDriver)
    {
        txpDriver.setUpdateTime(DateUtils.getNowDate());
        return txpDriverMapper.updateTxpDriver(txpDriver);
    }

    /**
     * 批量删除驾驶员信息
     *
     * @param driverIds 需要删除的驾驶员信息主键
     * @return 结果
     */
    @Override
    public int deleteTxpDriverByDriverIds(Long[] driverIds)
    {
        return txpDriverMapper.deleteTxpDriverByDriverIds(driverIds);
    }

    /**
     * 删除驾驶员信息信息
     *
     * @param driverId 驾驶员信息主键
     * @return 结果
     */
    @Override
    public int deleteTxpDriverByDriverId(Long driverId)
    {
        return txpDriverMapper.deleteTxpDriverByDriverId(driverId);
    }
}

