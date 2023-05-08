package com.ruoyi.txp.service;

import java.util.List;
import com.ruoyi.txp.domain.TxpDriver;

/**
 * 驾驶员信息Service接口
 *
 * @author ruoyi
 * @date 2023-03-17
 */
public interface ITxpDriverService
{
    /**
     * 查询驾驶员信息
     *
     * @param driverId 驾驶员信息主键
     * @return 驾驶员信息
     */
    public TxpDriver selectTxpDriverByDriverId(Long driverId);

    /**
     * 查询驾驶员信息列表
     *
     * @param txpDriver 驾驶员信息
     * @return 驾驶员信息集合
     */
    public List<TxpDriver> selectTxpDriverList(TxpDriver txpDriver);

    /**
     * 新增驾驶员信息
     *
     * @param txpDriver 驾驶员信息
     * @return 结果
     */
    public int insertTxpDriver(TxpDriver txpDriver);

    /**
     * 修改驾驶员信息
     *
     * @param txpDriver 驾驶员信息
     * @return 结果
     */
    public int updateTxpDriver(TxpDriver txpDriver);

    /**
     * 批量删除驾驶员信息
     *
     * @param driverIds 需要删除的驾驶员信息主键集合
     * @return 结果
     */
    public int deleteTxpDriverByDriverIds(Long[] driverIds);

    /**
     * 删除驾驶员信息信息
     *
     * @param driverId 驾驶员信息主键
     * @return 结果
     */
    public int deleteTxpDriverByDriverId(Long driverId);
}
