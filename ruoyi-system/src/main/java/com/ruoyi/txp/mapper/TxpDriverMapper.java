package com.ruoyi.txp.mapper;

import java.util.List;
import com.ruoyi.txp.domain.TxpDriver;

/**
 * 驾驶员信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-17
 */
public interface TxpDriverMapper
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
     * 删除驾驶员信息
     *
     * @param driverId 驾驶员信息主键
     * @return 结果
     */
    public int deleteTxpDriverByDriverId(Long driverId);

    /**
     * 批量删除驾驶员信息
     *
     * @param driverIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTxpDriverByDriverIds(Long[] driverIds);
}

