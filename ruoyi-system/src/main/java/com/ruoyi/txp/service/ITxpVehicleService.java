package com.ruoyi.txp.service;

import java.util.List;
import com.ruoyi.txp.domain.TxpVehicle;

/**
 * 车辆信息Service接口
 *
 * @author ruoyi
 * @date 2023-03-17
 */
public interface ITxpVehicleService
{
    /**
     * 查询车辆信息
     *
     * @param vehicleId 车辆信息主键
     * @return 车辆信息
     */
    public TxpVehicle selectTxpVehicleByVehicleId(Long vehicleId);

    /**
     * 查询车辆信息列表
     *
     * @param txpVehicle 车辆信息
     * @return 车辆信息集合
     */
    public List<TxpVehicle> selectTxpVehicleList(TxpVehicle txpVehicle);

    /**
     * 新增车辆信息
     *
     * @param txpVehicle 车辆信息
     * @return 结果
     */
    public int insertTxpVehicle(TxpVehicle txpVehicle);

    /**
     * 修改车辆信息
     *
     * @param txpVehicle 车辆信息
     * @return 结果
     */
    public int updateTxpVehicle(TxpVehicle txpVehicle);

    /**
     * 批量删除车辆信息
     *
     * @param vehicleIds 需要删除的车辆信息主键集合
     * @return 结果
     */
    public int deleteTxpVehicleByVehicleIds(Long[] vehicleIds);

    /**
     * 删除车辆信息信息
     *
     * @param vehicleId 车辆信息主键
     * @return 结果
     */
    public int deleteTxpVehicleByVehicleId(Long vehicleId);

    /**
     * 展示车辆信息列表
     *
     * @param txpVehicle 车辆信息
     * @return 车辆信息集合
     */
    public List<TxpVehicle> showTxpVehicleList(TxpVehicle txpVehicle);
}

